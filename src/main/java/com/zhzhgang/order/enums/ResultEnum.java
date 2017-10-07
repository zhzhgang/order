package com.zhzhgang.order.enums;

import lombok.Getter;

/**
 * Created by zhangzhonggang
 * 2017-10-07 16:13
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确")
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
