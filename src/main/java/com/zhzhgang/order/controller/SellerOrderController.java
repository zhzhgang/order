package com.zhzhgang.order.controller;

import com.github.pagehelper.PageInfo;
import com.zhzhgang.order.converter.OrderMaster2OrderDTOConverter;
import com.zhzhgang.order.domain.OrderMaster;
import com.zhzhgang.order.dto.OrderDTO;
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
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Map<String, Object> map) {

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setPage(page);
        orderMaster.setRows(size);

        List<OrderMaster> orderMasterList = orderService.findOrderList(orderMaster);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(orderMasterList);
        map.put("pageInfo", pageInfo);

        return new ModelAndView("order/list", map);
    }
}
