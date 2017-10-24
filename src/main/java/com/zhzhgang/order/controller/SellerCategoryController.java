package com.zhzhgang.order.controller;

import com.zhzhgang.order.domain.ProductCategory;
import com.zhzhgang.order.domain.ProductInfo;
import com.zhzhgang.order.exception.OrderException;
import com.zhzhgang.order.form.CategoryForm;
import com.zhzhgang.order.form.ProductForm;
import com.zhzhgang.order.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhonggang
 * 2017-10-24 14:39
 */
@Controller
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 跳转类目编辑页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {

        if (categoryId != null) {
            try {
                ProductCategory productCategory = productCategoryService.findById(categoryId);
                map.put("productCategory", productCategory);
            } catch (OrderException e) {
                map.put("msg", e.getMessage());
                map.put("url", "/seller/category/list");
                return new ModelAndView("common/error", map);
            }
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if (form.getCategoryId() == null) {
                BeanUtils.copyProperties(form, productCategory);
                productCategoryService.save(productCategory);
            } else {
                productCategory = productCategoryService.findById(form.getCategoryId());
                BeanUtils.copyProperties(form, productCategory);
                productCategoryService.update(productCategory);
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", form.getCategoryId() == null ? "/sell/seller/category/index" : "/sell/seller/category/index?categoryId=" + form.getCategoryId());
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
