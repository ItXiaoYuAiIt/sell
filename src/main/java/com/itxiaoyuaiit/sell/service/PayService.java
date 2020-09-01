package com.itxiaoyuaiit.sell.service;

import com.itxiaoyuaiit.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:59
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
