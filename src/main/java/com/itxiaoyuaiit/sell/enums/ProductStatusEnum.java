package com.itxiaoyuaiit.sell.enums;

import lombok.Getter;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:56
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0, "在架"),
    DOWN(1, "下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
