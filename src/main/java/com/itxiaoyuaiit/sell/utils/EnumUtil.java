package com.itxiaoyuaiit.sell.utils;

import com.itxiaoyuaiit.sell.enums.CodeEnum;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 16:00
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
