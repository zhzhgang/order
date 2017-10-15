package com.zhzhgang.order.vo;

import lombok.Data;

/**
 * Http 请求返回结果对象
 * Created by zhangzhonggang
 * 2017-10-06 0:21
 */
@Data
public class ResultVO<T> {

    /** 结果码. */
    private Integer code;

    /** 结果信息. */
    private String msg;

    /** 结果数据. */
    private T data;
}
