package com.itxiaoyuaiit.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itxiaoyuaiit.sell.dataobject.OrderDetail;
import com.itxiaoyuaiit.sell.dto.OrderDTO;
import com.itxiaoyuaiit.sell.enums.ResultEnum;
import com.itxiaoyuaiit.sell.exception.SellException;
import com.itxiaoyuaiit.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:54
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
