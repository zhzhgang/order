package com.zhzhgang.order.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhzhgang.order.domain.ProductInfo;
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
 * 2017-10-05 23:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void testSave() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(UUID.randomUUID().toString().substring(0, 13) + System.currentTimeMillis());
        productInfo.setProductName("烤肉");
        productInfo.setProductPrice(new BigDecimal(30));
        productInfo.setProductStock(100);
        productInfo.setProductDesc("一起撸串吧");
        productInfo.setProductIcon("http://ddd.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);

        productInfoService.save(productInfo);
    }

    @Test
    public void testFindById() throws Exception {
        ProductInfo result = productInfoService.findById("877d218d-558f1507214738788");
        Assert.assertTrue("扎啤".equals(result.getProductName()));
    }

    @Test
    public void testFindUpAll() throws Exception {
        List<ProductInfo> result = productInfoService.findUpAll();
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void testFindAll() throws Exception {
        PageInfo<ProductInfo> result = new PageInfo<ProductInfo>(productInfoService.findAll());
        Assert.assertEquals(2, result.getNextPage());
    }

}