package com.zhzhgang.order.controller;

import com.zhzhgang.order.converter.OrderForm2OrderDTOConverter;
import com.zhzhgang.order.converter.OrderMaster2OrderDTOConverter;
import com.zhzhgang.order.domain.OrderMaster;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
    public ResultVO<Map<String, String>> create(@RequestBody@Valid OrderForm orderForm,
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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid 为空");
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setPage(page);
        orderMaster.setRows(size);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderService.findOrderList(openid, orderMaster));
        return ResultVOUtil.success(orderDTOList);
    }

    // 订单详情
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {

        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单详情】openid 为空");
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(orderId)) {
            log.error("【查询订单详情】orderId 为空");
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        // TODO 不安全
        return ResultVOUtil.success(orderService.findByOrderId(orderId));
    }

    // 取消订单

}
