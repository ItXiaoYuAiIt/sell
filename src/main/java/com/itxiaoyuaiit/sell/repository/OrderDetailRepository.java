package com.itxiaoyuaiit.sell.repository;

import com.itxiaoyuaiit.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:57
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
