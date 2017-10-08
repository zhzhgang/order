package com.zhzhgang.order.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhzhgang.order.domain.OrderMaster;
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
 * 2017-10-07 0:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(UUID.randomUUID().toString().substring(0, 13) + System.currentTimeMillis());
        orderMaster.setBuyerName("李四");
        orderMaster.setBuyerPhone("18012345678");
        orderMaster.setBuyerAddress("招商局广场");
        orderMaster.setBuyerOpenId("abcd123");
        orderMaster.setOrderAmount(new BigDecimal(75));

        orderMasterDao.save(orderMaster);
    }

    @Test
    public void testFindByBuyerOpenId() throws Exception {
        PageHelper.startPage(2, 2);
        List<OrderMaster> result = orderMasterDao.findByBuyerOpenId("abcd123");
        PageInfo<OrderMaster> pageInfo = new PageInfo(result);
        System.out.println(pageInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void testFindByOrderId() {
        OrderMaster orderMaster = orderMasterDao.findByOrderId("1507370234106553097");
        Assert.assertNotNull(orderMaster);
    }
}