package com.zhzhgang.order.service.impl;

import com.zhzhgang.order.dto.OrderDTO;
import com.zhzhgang.order.enums.ResultEnum;
import com.zhzhgang.order.exception.OrderException;
import com.zhzhgang.order.service.BuyerService;
import com.zhzhgang.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by zhangzhonggang
 * 2017-10-17 10:49
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {


    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】订单不存在，orderId = {}", orderId);
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {

        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单】openid 为空");
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(orderId)) {
            log.error("【查询订单】orderId 为空");
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        if (orderDTO == null) {
            return null;
        }

        if (!orderDTO.getBuyerOpenId().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的 openid 不一致，openid = {}, orderDTO = {}", openid, orderDTO);
            throw new OrderException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
