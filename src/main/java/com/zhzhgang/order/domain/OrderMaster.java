package com.zhzhgang.order.domain;

import com.zhzhgang.order.enums.OrderStatusEnum;
import com.zhzhgang.order.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangzhonggang
 * 2017-10-06 18:23
 */
@Data
public class OrderMaster {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /** 订单状态，默认为 0，新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态，默认为 0，等待支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date ctime;

    private Date utime;
}
