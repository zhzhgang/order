package com.zhzhgang.order.dao;

import com.zhzhgang.order.domain.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-04 14:26
 */
public interface ProductCategoryDao {

    ProductCategory findById(Integer id);

    List<ProductCategory> findAll();

    void save(ProductCategory productCategory);

    void update(ProductCategory productCategory);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
