package com.zhzhgang.order.enums;

import lombok.Getter;

/**
 * 商品状态
 * Created by zhangzhonggang
 * 2017-10-05 22:54
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0, "在架"),
    DOWN(1, "下架")
    ;

    private Integer code;

    private String msg;

    ProductStatusEnum(Integer code) {
        this.code = code;
    }

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
