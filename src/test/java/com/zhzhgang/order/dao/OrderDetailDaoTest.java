package com.zhzhgang.order.dao;

import com.zhzhgang.order.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by zhangzhonggang
 * 2017-10-07 1:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void testSave() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(UUID.randomUUID().toString().substring(0, 13) + System.currentTimeMillis());
        orderDetail.setOrderId("206c4d7c-f52b1507309490534");
        orderDetail.setProductId("ac8b63bf-aafe1507216860463");
        orderDetail.setProductName("烤肉");
        orderDetail.setProductPrice(new BigDecimal(30));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductIcon("http://ddd.jpg");

        orderDetailDao.save(orderDetail);
    }

    @Test
    public void testFindByOrderId() throws Exception {
        List<OrderDetail> result = orderDetailDao.findByOrderId("206c4d7c-f52b1507309490534");
        System.out.println(result);
        Assert.assertNotEquals(0, result.size());
    }

}