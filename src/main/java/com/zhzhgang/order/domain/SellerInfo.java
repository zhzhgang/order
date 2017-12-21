package com.zhzhgang.order.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhangzhonggang
 * 2017-12-21 16:43
 */
@Data
public class SellerInfo {

    /** 卖家 Id. */
    private String sellerId;

    /** 卖家姓名. */
    private String username;

    /** 密码. */
    private String password;

    /** 卖家 openid. */
    private String openid;

    private Date ctime;

    private Date utime;
}
