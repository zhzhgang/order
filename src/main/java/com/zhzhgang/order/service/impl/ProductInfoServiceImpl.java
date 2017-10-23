package com.zhzhgang.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhzhgang.order.dao.ProductInfoDao;
import com.zhzhgang.order.domain.ProductInfo;
import com.zhzhgang.order.dto.CartDTO;
import com.zhzhgang.order.enums.ProductStatusEnum;
import com.zhzhgang.order.enums.ResultEnum;
import com.zhzhgang.order.exception.OrderException;
import com.zhzhgang.order.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ProductInfo> findAll(ProductInfo productInfo) {
        if (productInfo.getPage() != null && productInfo.getRows() != null) {
            PageHelper.startPage(productInfo.getPage(), productInfo.getRows());
        }
        return productInfoDao.findAll();
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new OrderException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            productInfo.setProductStock(productInfo.getProductStock() + cartDTO.getProductQuantity());
            productInfoDao.update(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new OrderException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new OrderException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoDao.update(productInfo);
        }
    }

    /**
     * 上架
     *
     * @param productId
     * @return
     */
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoDao.findById(productId);
        if (productInfo == null) {
            throw new OrderException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus().equals(ProductStatusEnum.UP.getCode())) {
            throw new OrderException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoDao.update(productInfo);
        return productInfo;
    }

    /**
     * 下架
     *
     * @param productId
     * @return
     */
    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoDao.findById(productId);
        if (productInfo == null) {
            throw new OrderException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())) {
            throw new OrderException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfoDao.update(productInfo);
        return productInfo;
    }
}
