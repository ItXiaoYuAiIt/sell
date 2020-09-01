package com.itxiaoyuaiit.sell.exception;

import com.itxiaoyuaiit.sell.enums.ResultEnum;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:56
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
