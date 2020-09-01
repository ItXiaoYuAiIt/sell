package com.itxiaoyuaiit.sell.VO;

import lombok.Data;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 16:00
 */
@Data
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
