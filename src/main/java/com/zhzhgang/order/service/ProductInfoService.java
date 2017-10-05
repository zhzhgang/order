package com.zhzhgang.order.service;

import com.zhzhgang.order.domain.ProductInfo;
import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-05 17:18
 */
public interface ProductInfoService {

    void save(ProductInfo productInfo);

    ProductInfo findById(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    List<ProductInfo> findAll();

    // 加库存

    // 减库存

}
