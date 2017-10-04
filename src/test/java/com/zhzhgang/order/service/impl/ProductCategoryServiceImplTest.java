package com.zhzhgang.order.service.impl;

import com.zhzhgang.order.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.PortUnreachableException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhangzhonggang
 * 2017-10-04 23:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findByid() throws Exception {
        ProductCategory result = productCategoryService.findByid(2);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> result = productCategoryService.findAll();
        Assert.assertNotNull(result.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> result = productCategoryService.findByCategoryTypeIn(Arrays.asList(1, 2));
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("体育用品", 4);
        productCategoryService.save(productCategory);
        Assert.assertNotNull(productCategory.getCategoryId());
    }

    @Test
    public void update() throws Exception {
        ProductCategory productCategory = productCategoryService.findByid(4);
        productCategory.setCategoryName("母婴用品");
        productCategoryService.update(productCategory);
        Assert.assertEquals("母婴用品", productCategoryService.findByid(4).getCategoryName());
    }

}