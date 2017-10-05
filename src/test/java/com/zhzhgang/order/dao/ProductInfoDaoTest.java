package com.zhzhgang.order.dao;

import com.zhzhgang.order.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.rmi.server.UID;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by zhangzhonggang
 * 2017-10-05 16:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    ProductInfoDao productInfoDao;

    @Test
    public void testSave() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(UUID.randomUUID().toString().substring(0, 13) + System.currentTimeMillis());
        productInfo.setProductName("冰激凌");
        productInfo.setProductPrice(new BigDecimal(15));
        productInfo.setProductStock(100);
        productInfo.setProductDesc("巧克力冰激凌");
        productInfo.setProductIcon("http://bbb.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        productInfoDao.save(productInfo);
    }

    @Test
    public void testFindByProductStatus() throws Exception {
        List<ProductInfo> result = productInfoDao.findByProductStatus(0);
        Assert.assertSame(2, result.size());
    }

}