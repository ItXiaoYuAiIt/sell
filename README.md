# sell
基于springboot的微信点餐系统，旨在学习springboot以及微信公众号开发相关知识

包含：

1. springboot：表单验证、统一异常处理、事物管理、restful Api开发
2. 模板引擎springframework使用等知识
3. jpa mybatis mysql数据库操作
4. redis缓存分布式锁
5. 微信授权、微信支付、微信退款等相关知识


# 开发环境
1. 虚拟机：centos7.3
   + jdk 1.8.0_111
   + nginx 1.11.7
   + mysql 5.7.17
   + redis 3.2.8

## 数据库表设计
数据库版本：5.7.17

数据库名：sell

数据库字符集：utf8mb4

数据库表：

```mysql
-- 类目
create table `product_category` (
    `category_id` int not null auto_increment,
    `category_name` varchar(64) not null comment '类目名字',
    `category_type` int not null comment '类目编号',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`category_id`)
);

-- 商品
create table `product_info` (
    `product_id` varchar(32) not null,
    `product_name` varchar(64) not null comment '商品名称',
    `product_price` decimal(8,2) not null comment '单价',
    `product_stock` int not null comment '库存',
    `product_description` varchar(64) comment '描述',
    `product_icon` varchar(512) comment '小图',
    `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
    `category_type` int not null comment '类目编号',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`product_id`)
);

-- 订单
create table `order_master` (
    `order_id` varchar(32) not null,
    `buyer_name` varchar(32) not null comment '买家名字',
    `buyer_phone` varchar(32) not null comment '买家电话',
    `buyer_address` varchar(128) not null comment '买家地址',
    `buyer_openid` varchar(64) not null comment '买家微信openid',
    `order_amount` decimal(8,2) not null comment '订单总金额',
    `order_status` tinyint(3) not null default '0' comment '订单状态, 默认为新下单',
    `pay_status` tinyint(3) not null default '0' comment '支付状态, 默认未支付',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`order_id`),
    key `idx_buyer_openid` (`buyer_openid`)
);

-- 订单商品
create table `order_detail` (
    `detail_id` varchar(32) not null,
    `order_id` varchar(32) not null,
    `product_id` varchar(32) not null,
    `product_name` varchar(64) not null comment '商品名称',
    `product_price` decimal(8,2) not null comment '当前价格,单位分',
    `product_quantity` int not null comment '数量',
    `product_icon` varchar(512) comment '小图',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`detail_id`),
    key `idx_order_id` (`order_id`)
);

-- 卖家(登录后台使用, 卖家登录之后可能直接采用微信扫码登录，不使用账号密码)
create table `seller_info` (
    `id` varchar(32) not null,
    `username` varchar(32) not null,
    `password` varchar(32) not null,
    `openid` varchar(64) not null comment '微信openid',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`id`)
) comment '卖家信息表';
```
## 日志框架选择
SL4J + Logbak

原因：SL4J 与 Logbak都是同一个作者开发，能够无缝对接，Log4j2功能强大性能优秀，但是Logbak才是真正意义上的Log4j的升级版本，所以这里选择SL4J + Logbak的组合

1. 新建logback.xml日志文件配置
```xml
<?xml version="1.0" encoding="UTF-8" ?>

<configuration>

    <appender name="consoleLog" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>
                %d - %msg%n
            </pattern>
        </layout>
    </appender>

    <appender name="fileInfoLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>DENY</onMatch>
            <onMismatch>ACCEPT</onMismatch>
        </filter>
        <encoder>
            <pattern>
                %msg%n
            </pattern>
        </encoder>
        <!--滚动策略-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--路径-->
            <fileNamePattern>./log/sell/info.%d.log</fileNamePattern>
        </rollingPolicy>
    </appender>


    <appender name="fileErrorLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>
        <encoder>
            <pattern>
                %msg%n
            </pattern>
        </encoder>
        <!--滚动策略-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--路径-->
            <fileNamePattern>./log/sell/error.%d.log</fileNamePattern>
        </rollingPolicy>
    </appender>

    <root level="info">
        <appender-ref ref="consoleLog" />
        <appender-ref ref="fileInfoLog" />
        <appender-ref ref="fileErrorLog" />
    </root>

</configuration>
```

1. 日志框架测试
```java
package com.itxiaoyuaiit.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1() {
        String name = "springboot";
        String password = "123456";
        log.debug("debug...");
        log.info("name: " + name + " ,password: " + password);
        log.info("name: {}, password: {}", name, password);
        log.error("error...");
        log.warn("warn...");
    }
}

```
## 买家商品类目Api开发
1. 添加jpa和mysql connect依赖
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>

```
2. 修改application.properties为application.yml文件（个人习惯，你也可以使用application.properties文件）
3. 添加数据库资源配置
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: 123456
    url: jdbc:mysql://192.168.30.113/sell?characterEncoding=utf-8&useSSL=false
  jpa:
    show-sql: true
```
4. 添加数据库口令加密依赖
   这里采用jasypt来进行数据库加密，淡然如果您选用的是Druid数据库连接池的话你也可以采用其自带的加解密方式

```xml
        <!--明文配置密码加密解密-->
        <dependency>
            <groupId>com.github.ulisesbocchio</groupId>
            <artifactId>jasypt-spring-boot-starter</artifactId>
            <version>1.8</version>
        </dependency>
```
5. 配置加密加盐

```yaml
#jasypt加密的盐值   
jasypt:
  encryptor:
    password: sell
```
6. 编写测试类进行加密

```java
package com.itxiaoyuaiit.sell;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JasyptTest {

    @Autowired
    private StringEncryptor encryptor;


    @Test
    public void getPass() {
        String name = "*****";
        String password = "*****";
        log.info("name: " + name + " ,password: " + password);
        log.info("name: {}, password: {}", encryptor.encrypt(name), encryptor.encrypt(password));

    }
}

```

7. ProductCategoryRepository开发

```java
package com.itxiaoyuaiit.sell.repository;

import com.itxiaoyuaiit.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:57
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}

```

8. CategoryService

```java
package com.itxiaoyuaiit.sell.service;

import com.itxiaoyuaiit.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:58
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}

```

9. CategoryServiceImpl开发

```java
package com.itxiaoyuaiit.sell.service.impl;

import com.itxiaoyuaiit.sell.dataobject.ProductCategory;
import com.itxiaoyuaiit.sell.repository.ProductCategoryRepository;
import com.itxiaoyuaiit.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:58
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}

```

10. 编写测试类CategoryServiceImplTest进行单元测试

```java
package com.itxiaoyuaiit.sell.service.impl;

import com.itxiaoyuaiit.sell.dataobject.ProductCategory;
import com.itxiaoyuaiit.sell.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory one = categoryService.findOne(1);
        log.info(one.toString());
        Assert.assertTrue("根据id查询商品类目", one.getCategoryId()==1);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByCategoryTypeIn() {
    }

    @Test
    public void save() {
    }
}
```

+ 这里只列举了对商品类目Api开发相关流程，其余业务请[查阅代码](git@github.com:ItXiaoYuAiIt/sell.git)。

