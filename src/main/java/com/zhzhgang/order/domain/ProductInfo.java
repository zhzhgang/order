package com.zhzhgang.order.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品信息实体类
 * Created by zhangzhonggang
 * 2017-10-05 15:48
 */
@Data
public class ProductInfo extends BaseEntity {

    private String productId;

    /** 商品名称. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDesc;

    /** 小图. */
    private String productIcon;

    /** 状态，0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;
}
