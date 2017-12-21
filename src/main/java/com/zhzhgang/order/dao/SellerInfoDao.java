package com.zhzhgang.order.dao;

import com.zhzhgang.order.domain.SellerInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangzhonggang
 * 2017-12-21 16:49
 */
@Repository
public interface SellerInfoDao {

    void save(SellerInfo sellerInfo);

    SellerInfo findByOpenid(String openid);
}
