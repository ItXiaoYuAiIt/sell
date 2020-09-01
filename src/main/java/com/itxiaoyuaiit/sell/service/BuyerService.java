package com.itxiaoyuaiit.sell.service;

import com.itxiaoyuaiit.sell.dto.OrderDTO;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:58
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
