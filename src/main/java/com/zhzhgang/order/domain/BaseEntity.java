package com.zhzhgang.order.domain;

import lombok.Data;

/**
 * Created by zhangzhonggang
 * 2017-10-08 16:16
 */
@Data
public class BaseEntity {

    private Integer page = 1;

    private Integer rows = 10;
}
