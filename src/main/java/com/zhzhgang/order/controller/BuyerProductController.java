package com.zhzhgang.order.controller;

import com.zhzhgang.order.VO.ProductInfoVO;
import com.zhzhgang.order.VO.ProductVO;
import com.zhzhgang.order.VO.ResultVO;
import com.zhzhgang.order.domain.ProductInfo;
import com.zhzhgang.order.service.impl.ProductInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 买家端商品 controller
 * Created by zhangzhonggang
 * 2017-10-05 23:56
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultVO<List<ProductVO>> list() {
        ResultVO<List<ProductVO>> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");

        List<ProductVO> productVOList = new ArrayList<ProductVO>();
        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();
        List<ProductInfoVO> productInfoVOList = new ArrayList<>();


        productInfoVOList.add(productInfoVO);
        productVO.setProductInfoVOList(productInfoVOList);
        productVOList.add(productVO);
        resultVO.setData(productVOList);

        return resultVO;
    }


}
