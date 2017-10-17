package com.zhzhgang.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhzhgang.order.dao.OrderDetailDao;
import com.zhzhgang.order.dao.OrderMasterDao;
import com.zhzhgang.order.domain.OrderDetail;
import com.zhzhgang.order.domain.OrderMaster;
import com.zhzhgang.order.domain.ProductInfo;
import com.zhzhgang.order.dto.CartDTO;
import com.zhzhgang.order.dto.OrderDTO;
import com.zhzhgang.order.enums.OrderStatusEnum;
import com.zhzhgang.order.enums.PayStatusEnum;
import com.zhzhgang.order.enums.ResultEnum;
import com.zhzhgang.order.exception.OrderException;
import com.zhzhgang.order.service.OrderService;
import com.zhzhgang.order.service.ProductInfoService;
import com.zhzhgang.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangzhonggang
 * 2017-10-07 15:58
 */
@Service
@Slf4j
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
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);

        // 1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findById(orderDetail.getProductId());
            if (productInfo == null) {
                throw new OrderException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 2. 计算订单总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            // 3. 订单详情入库（order_detail）
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);

            orderDetailDao.save(orderDetail);
        }

        // 4. 写入订单数据库（orderMaster）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterDao.save(orderMaster);

        // 5. 扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);


        return orderDTO;
    }

    /**
     * 查询单个订单.
     *
     * @param orderId
     */
    @Override
    public OrderDTO findByOrderId(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findByOrderId(orderId);
        if (orderMaster == null) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    /**
     * 查询订单列表.
     *
     * @param openId
     */
    @Override
    public List<OrderMaster> findOrderList(String openId, OrderMaster orderMaster) {
        if (orderMaster.getPage() != null && orderMaster.getRows() != null) {
            PageHelper.startPage(orderMaster.getPage(), orderMaster.getRows());
        }
        return orderMasterDao.findByBuyerOpenId(openId);
    }

    /**
     * 取消订单.
     *
     * @param orderDTO
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        // 1. 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 2. 修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        try {
            orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
            BeanUtils.copyProperties(orderDTO, orderMaster);
            orderMasterDao.update(orderMaster);
        } catch (Exception e) {
            log.error("【取消订单】更新失败，orderMaster = {}", orderMaster, e);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 3. 返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情，orderDTO = {}", orderDTO);
            throw new OrderException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        try {
            productInfoService.increaseStock(cartDTOList);
        } catch (Exception e) {
            log.error("【取消订单】更新失败，cartDTOList = {}", cartDTOList, e);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 4. 如果已支付，退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            // TODO
        }
        return orderDTO;
    }

    /**
     * 完结订单.
     *
     * @param orderDTO
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 1. 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 2. 修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        try {
            orderMasterDao.update(orderMaster);
        } catch (Exception e) {
            log.error("【完结订单】更新失败，orderMaster = {}", orderMaster, e);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    /**
     * 支付订单.
     *
     * @param orderDTO
     */
    @Override
    @Transactional
    public OrderDTO pay(OrderDTO orderDTO) {
        // 1. 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确，orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 2. 判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】支付状态不正确，orderId = {}, payStatus = {}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new OrderException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 3. 修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        try {
            orderMasterDao.update(orderMaster);
        } catch (Exception e) {
            log.error("【支付订单】更新失败，orderMaster = {}", orderMaster, e);
            throw new OrderException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO ;
    }

    /**
     * 查询订单列表（卖家端）.
     *
     * @param orderMaster
     */
    @Override
    public List<OrderMaster> findOrderList(OrderMaster orderMaster) {
        if (orderMaster.getPage() != null && orderMaster.getRows() != null) {
            PageHelper.startPage(orderMaster.getPage(), orderMaster.getRows());
        }
        return orderMasterDao.findAll();
    }
}
