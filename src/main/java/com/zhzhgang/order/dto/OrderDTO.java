package com.zhzhgang.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhzhgang.order.domain.BaseEntity;
import com.zhzhgang.order.domain.OrderDetail;
import com.zhzhgang.order.enums.OrderStatusEnum;
import com.zhzhgang.order.enums.PayStatusEnum;
import com.zhzhgang.order.utils.EnumUtil;
import com.zhzhgang.order.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-07 15:49
 */
@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    @JsonProperty("buyerOpenid")
    private String buyerOpenId;

    private BigDecimal orderAmount;

    /** 订单状态，默认为 0，新下单. */
    private Integer orderStatus;

    /** 支付状态，默认为 0，等待支付. */
    private Integer payStatus;

    @JsonProperty("createTime")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date ctime;

    @JsonProperty("updateTime")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date utime;

    private List<OrderDetail> orderDetailList;
}
