package com.zhzhgang.order.service.impl;

import com.zhzhgang.order.dao.SellerInfoDao;
import com.zhzhgang.order.domain.SellerInfo;
import com.zhzhgang.order.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangzhonggang
 * 2017-12-21 17:23
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    /**
     * 根据 openid 查询卖家信息
     *
     * @param openid
     * @return
     */
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }

}
