package com.itxiaoyuaiit.sell.utils;

import java.util.Random;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 16:00
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
