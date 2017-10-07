package com.zhzhgang.order.exception;

import com.zhzhgang.order.enums.ResultEnum;

/**
 * Created by zhangzhonggang
 * 2017-10-07 16:14
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
