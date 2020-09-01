package com.itxiaoyuaiit.sell.repository;

import com.itxiaoyuaiit.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        log.info(productCategory.toString());
    }

    @Test
    public void create() {
        ProductCategory productCategory = new ProductCategory("最实惠", 2);
        ProductCategory save = productCategoryRepository.save(productCategory);
        log.info(save.toString());
    }

    @Test
    public void update() {
        ProductCategory productCategory = productCategoryRepository.findOne(2);
        productCategory.setCategoryType(5);
        ProductCategory save = productCategoryRepository.save(productCategory);
        log.info(save.toString());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> categoryList = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(1, 5));
        Assert.assertTrue("根据商品类目编号查询商品类目", categoryList.size() > 0);
    }
}