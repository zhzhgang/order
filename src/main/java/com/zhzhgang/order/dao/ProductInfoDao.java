package com.zhzhgang.order.dao;

import com.zhzhgang.order.domain.ProductInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-05 16:04
 */
@Repository
public interface ProductInfoDao {

    void save(ProductInfo productInfo);

    void update(ProductInfo productInfo);

    ProductInfo findById(String productId);

    List<ProductInfo> findAll();

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
