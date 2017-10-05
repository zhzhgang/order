package com.zhzhgang.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhzhgang.order.dao.ProductInfoDao;
import com.zhzhgang.order.domain.ProductInfo;
import com.zhzhgang.order.enums.ProductStatusEnum;
import com.zhzhgang.order.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-05 17:19
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public void save(ProductInfo productInfo) {
        productInfoDao.save(productInfo);
    }

    @Override
    public ProductInfo findById(String productId) {
        return productInfoDao.findById(productId);
    }

    /**
     * 查询所有在架商品列表
     *
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findAll() {
        PageHelper.startPage(0, 1);
        return productInfoDao.findAll();
    }
}
