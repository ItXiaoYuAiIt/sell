package com.itxiaoyuaiit.sell.enums;

import lombok.Getter;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:56
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
