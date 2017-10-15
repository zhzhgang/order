package com.zhzhgang.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhzhgang.order.domain.OrderDetail;
import com.zhzhgang.order.dto.OrderDTO;
import com.zhzhgang.order.enums.ResultEnum;
import com.zhzhgang.order.exception.OrderException;
import com.zhzhgang.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-15 21:53
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenId(orderForm.getOpenid());

        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误，string = {}", orderForm.getItems(), e);
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
