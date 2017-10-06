package com.zhzhgang.order.enums;

import lombok.Getter;

/**
 * Created by zhangzhonggang
 * 2017-10-06 23:03
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功")
    ;

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
