package com.zhzhgang.order.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
        productInfo.setProductName("扎啤");
        productInfo.setProductPrice(new BigDecimal(45));
        productInfo.setProductStock(100);
        productInfo.setProductDesc("一起哈啤吧");
        productInfo.setProductIcon("http://ccc.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);

        productInfoDao.save(productInfo);
    }

    @Test
    public void testFindByProductStatus() throws Exception {
        List<ProductInfo> result = productInfoDao.findByProductStatus(0);
        Assert.assertSame(2, result.size());
    }

    @Test
    public void testFindById() {
        ProductInfo result = productInfoDao.findById("16b3f8f2-cd151507194585011");
        Assert.assertNotNull(result);
    }

    @Test
    public void testFindAll() {
        PageHelper.startPage(2, 2);
        List<ProductInfo> result = productInfoDao.findAll();
        System.out.println("Total: " + ((Page) result).getTotal());
        for (ProductInfo productInfo : result) {
            System.out.println("product name: " + productInfo.getProductName());
        }
        Assert.assertNotNull(result.size());
    }

}