package com.zhzhgang.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhzhgang.order.enums.OrderStatusEnum;
import com.zhzhgang.order.enums.PayStatusEnum;
import com.zhzhgang.order.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangzhonggang
 * 2017-10-06 18:23
 */
@Data
public class OrderMaster extends BaseEntity {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenId;

    private BigDecimal orderAmount;

    /** 订单状态，默认为 0，新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态，默认为 0，等待支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date ctime;

    private Date utime;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
