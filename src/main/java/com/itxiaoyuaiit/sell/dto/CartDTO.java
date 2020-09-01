package com.itxiaoyuaiit.sell.dto;

import lombok.Data;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:55
 */
@Data
public class CartDTO {

    /** 商品Id. */
    private String productId;

    /** 数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
