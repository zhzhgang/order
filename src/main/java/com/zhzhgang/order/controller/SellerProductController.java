package com.zhzhgang.order.controller;

import com.github.pagehelper.PageInfo;
import com.zhzhgang.order.domain.ProductInfo;
import com.zhzhgang.order.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhonggang
 * 2017-10-23 16:58
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView List(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setPage(page);
        productInfo.setRows(size);

        List<ProductInfo> productInfoList = productInfoService.findAll(productInfo);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(productInfoList);

        map.put("pageInfo", pageInfo);

        return new ModelAndView("product/list", map);
    }
}
