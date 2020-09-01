package com.itxiaoyuaiit.sell.service;

import com.itxiaoyuaiit.sell.dto.OrderDTO;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:59
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
