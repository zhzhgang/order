package com.zhzhgang.order.converter;

import com.zhzhgang.order.domain.OrderDetail;
import com.zhzhgang.order.dto.OrderDTO;
import com.zhzhgang.order.form.OrderForm;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-15 21:53
 */
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenId(orderForm.getOpenid());

        List<OrderDetail> orderDetailList =
    }
}
