package com.zhzhgang.order.dao;

import com.zhzhgang.order.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhangzhonggang
 * 2017-10-04 14:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void testFindById() {
        ProductCategory productCategory = productCategoryDao.findById(1);
        System.out.println(productCategory);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public  void testSave() {
        ProductCategory productCategory = new ProductCategory("男生最爱", 3);
        System.out.println(productCategory.getCategoryId());
        productCategoryDao.save(productCategory);
        Assert.assertNotNull(productCategory.getCategoryId());
        System.out.println(productCategory.getCategoryId());
    }

    @Test
    public void testUpdate() {
        ProductCategory productCategory = productCategoryDao.findById(2);
        productCategory.setCategoryName("女生最爱");
        productCategoryDao.update(productCategory);
        System.out.println(productCategoryDao.findById(2));
    }

    @Test
    public void testFindByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<ProductCategory> result = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotNull(result.size());
    }

}