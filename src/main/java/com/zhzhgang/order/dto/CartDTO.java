package com.zhzhgang.order.dto;

import lombok.Data;

/**
 * 购物车
 * Created by zhangzhonggang
 * 2017-10-07 17:02
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
