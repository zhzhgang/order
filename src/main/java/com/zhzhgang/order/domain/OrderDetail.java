package com.zhzhgang.order.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by zhangzhonggang
 * 2017-10-06 23:09
 */
@Data
public class OrderDetail extends BaseEntity {

    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;
}
