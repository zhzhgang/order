package com.zhzhgang.order.service;

import com.zhzhgang.order.domain.ProductCategory;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-04 23:43
 */
public interface ProductCategoryService {

    ProductCategory findByid(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    void save(ProductCategory productCategory);

    void update(ProductCategory productCategory);
}
