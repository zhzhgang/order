package com.zhzhgang.order.service;

import com.zhzhgang.order.domain.OrderDetail;
import com.zhzhgang.order.dto.OrderDTO;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-07 15:44
 */
public interface OrderService {

    /** 创建订单. */
    OrderDTO create(OrderDTO orderDTO);

    /** 查询单个订单. */
    OrderDTO findByOrderId(String orderId);

    /** 查询订单列表. */
    List<OrderDTO> findOrderList(String openId);

    /** 取消订单. */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单. */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单. */
    OrderDTO pay(OrderDTO orderDTO);
}