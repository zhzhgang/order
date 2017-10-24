package com.zhzhgang.order.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhangzhonggang
 * 2017-10-24 10:16
 */
@Data
public class ProductForm {

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

    /** 类目编号. */
    private Integer categoryType;
}
