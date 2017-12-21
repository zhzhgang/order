package com.zhzhgang.order.service;

import com.zhzhgang.order.domain.SellerInfo;

/**
 * 卖家端
 * Created by zhangzhonggang
 * 2017-12-21 17:21
 */
public interface SellerService {

    /**
     * 根据 openid 查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
