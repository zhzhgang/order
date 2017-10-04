package com.zhzhgang.order.service.impl;

import com.zhzhgang.order.dao.ProductCategoryDao;
import com.zhzhgang.order.domain.ProductCategory;
import com.zhzhgang.order.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-04 23:47
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {


    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findByid(Integer id) {
        return productCategoryDao.findById(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryDao.save(productCategory);
    }

    @Override
    public void update(ProductCategory productCategory) {
        productCategoryDao.update(productCategory);
    }
}
