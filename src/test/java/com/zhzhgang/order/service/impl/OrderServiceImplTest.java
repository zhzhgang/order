package com.zhzhgang.order.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhzhgang.order.domain.OrderDetail;
import com.zhzhgang.order.domain.OrderMaster;
import com.zhzhgang.order.dto.OrderDTO;
import com.zhzhgang.order.enums.OrderStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhangzhonggang
 * 2017-10-07 17:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void testCreate() throws Exception {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerPhone("18788886666");
        orderDTO.setBuyerAddress("招商局广场");
        orderDTO.setBuyerOpenId("abc123");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail O1 = new OrderDetail();
        O1.setProductId("c7f1f7bf-9b761507194414616");
        O1.setProductQuantity(1);

        OrderDetail O2 = new OrderDetail();
        O2.setProductId("16b3f8f2-cd151507194585011");
        O2.setProductQuantity(1);
        orderDetailList.add(O1);
        orderDetailList.add(O2);

        orderDTO.setOrderDetailList(orderDetailList);

        Assert.assertNotNull(orderService.create(orderDTO));
    }

    @Test
    public void testFindByOrderId() throws Exception {
        String orderId = "1507389531839610036";
        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        Assert.assertEquals(2, orderDTO.getOrderDetailList().size());
    }

    @Test
    public void testFindOrderList() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setPage(1);
        orderMaster.setRows(2);

        List<OrderMaster> orderMasterList = orderService.findOrderList("abcd123", orderMaster);
        PageInfo<OrderMaster> orderMasterPageInfo = new PageInfo<>(orderMasterList);
        Assert.assertEquals(2, orderMasterPageInfo.getPages());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findByOrderId("1507389531839610036");
        orderDTO = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderDTO.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void pay() throws Exception {
    }

}