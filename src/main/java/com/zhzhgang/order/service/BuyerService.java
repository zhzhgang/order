package com.zhzhgang.order.service;

import com.zhzhgang.order.dto.OrderDTO;

/**
 * Created by zhangzhonggang
 * 2017-10-17 10:38
 */
public interface BuyerService {

    // 查询单个订单
    OrderDTO findOrderOne(String openid, String orderId);

    // 取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
