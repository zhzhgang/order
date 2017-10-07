package com.zhzhgang.order.service.impl;

import com.zhzhgang.order.dao.OrderDetailDao;
import com.zhzhgang.order.dao.OrderMasterDao;
import com.zhzhgang.order.dao.ProductInfoDao;
import com.zhzhgang.order.domain.OrderDetail;
import com.zhzhgang.order.domain.ProductInfo;
import com.zhzhgang.order.dto.OrderDTO;
import com.zhzhgang.order.service.OrderService;
import com.zhzhgang.order.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-07 15:58
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 创建订单.
     *
     * @param orderDTO
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        // 1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findById(orderDetail.getProductId());
        }

        // 2. 计算总价

        // 3. 写入订单数据库（order_master 和 order_detail）

        // 4. 扣库存

        return null;
    }

    /**
     * 查询单个订单.
     *
     * @param orderId
     */
    @Override
    public OrderDTO findByOrderId(String orderId) {
        return null;
    }

    /**
     * 查询订单列表.
     *
     * @param openId
     */
    @Override
    public List<OrderDTO> findOrderList(String openId) {
        return null;
    }

    /**
     * 取消订单.
     *
     * @param orderDTO
     */
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 完结订单.
     *
     * @param orderDTO
     */
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 支付订单.
     *
     * @param orderDTO
     */
    @Override
    public OrderDTO pay(OrderDTO orderDTO) {
        return null;
    }
}
