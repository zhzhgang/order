package com.zhzhgang.order.controller;

import com.zhzhgang.order.vo.ProductInfoVO;
import com.zhzhgang.order.vo.ProductVO;
import com.zhzhgang.order.vo.ResultVO;
import com.zhzhgang.order.domain.ProductCategory;
import com.zhzhgang.order.domain.ProductInfo;
import com.zhzhgang.order.service.ProductCategoryService;
import com.zhzhgang.order.service.ProductInfoService;
import com.zhzhgang.order.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家端商品 controller
 * Created by zhangzhonggang
 * 2017-10-05 23:56
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO<List<ProductVO>> list() {
        try {
            // 1. 查询所有上架商品
            List<ProductInfo> productInfoList = productInfoService.findUpAll();

            // 2. 查询类目（一次性查询）
            List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
            List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

            // 3. 数据拼装
            List<ProductVO> productVOList = new ArrayList<ProductVO>();
            for (ProductCategory productCategory : productCategoryList) {
                ProductVO productVO = new ProductVO();
                BeanUtils.copyProperties(productCategory, productVO);

                List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
                for (ProductInfo productInfo : productInfoList) {
                    if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                        ProductInfoVO productInfoVO = new ProductInfoVO();
                        BeanUtils.copyProperties(productInfo, productInfoVO);
                        productInfoVOList.add(productInfoVO);
                    }
                }
                productVO.setProductInfoVOList(productInfoVOList);
                productVOList.add(productVO);
            }
            return ResultVOUtil.success(productVOList);
        } catch (Exception e) {
            return ResultVOUtil.error(1, "失败");
        }
    }


}
