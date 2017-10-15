package com.zhzhgang.order.controller;

import com.zhzhgang.order.converter.OrderForm2OrderDTOConverter;
import com.zhzhgang.order.dto.OrderDTO;
import com.zhzhgang.order.enums.ResultEnum;
import com.zhzhgang.order.exception.OrderException;
import com.zhzhgang.order.form.OrderForm;
import com.zhzhgang.order.service.OrderService;
import com.zhzhgang.order.service.impl.OrderServiceImpl;
import com.zhzhgang.order.utils.ResultVOUtil;
import com.zhzhgang.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangzhonggang
 * 2017-10-15 21:15
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    // 创建订单
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDTO.getOrderId());

        return ResultVOUtil.success(map);
    }

    // 订单列表


    // 订单详情

    // 取消订单

}
