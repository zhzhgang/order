package com.zhzhgang.order.service;

import com.zhzhgang.order.domain.ProductInfo;
import com.zhzhgang.order.dto.CartDTO;

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

    List<ProductInfo> findAll(ProductInfo productInfo);

    // 加库存
    void increaseStock(List<CartDTO> cartDTOList);

    // 减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 上架
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

    void update(ProductInfo productInfo);
}
