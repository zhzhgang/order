package com.zhzhgang.order.controller;

import com.github.pagehelper.PageInfo;
import com.zhzhgang.order.converter.OrderMaster2OrderDTOConverter;
import com.zhzhgang.order.domain.OrderMaster;
import com.zhzhgang.order.dto.OrderDTO;
import com.zhzhgang.order.enums.ResultEnum;
import com.zhzhgang.order.exception.OrderException;
import com.zhzhgang.order.service.OrderService;
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
 * 2017-10-17 16:46
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页，从第 1 页开始
     * @param size 每页条数
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setPage(page);
        orderMaster.setRows(size);

        List<OrderMaster> orderMasterList = orderService.findOrderList(orderMaster);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(orderMasterList);
        map.put("pageInfo", pageInfo);

        return new ModelAndView("order/list", map);
    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @RequestMapping(value = "cancel", method = RequestMethod.GET)
    public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String, Object> map) {

        try {
            OrderDTO orderDTO = orderService.findByOrderId(orderId);
            orderService.cancel(orderDTO);
        } catch (OrderException e) {
            log.error("【卖家端取消订单】发生异常", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam("orderId") String orderId, Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findByOrderId(orderId);
            map.put("orderDTO", orderDTO);
        } catch (OrderException e) {
            log.error("【卖家端查看订单详情】发生异常", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("/order/detail", map);
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @RequestMapping(value = "finish", method = RequestMethod.GET)
    public ModelAndView finish(@RequestParam("orderId") String orderId, Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findByOrderId(orderId);
            orderService.finish(orderDTO);
        } catch (OrderException e) {
            log.error("【卖家端完结订单】发生异常", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
