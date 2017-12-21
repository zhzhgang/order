package com.zhzhgang.order.dao;

import com.zhzhgang.order.domain.SellerInfo;
import com.zhzhgang.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by zhangzhonggang
 * 2017-12-21 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void testSave() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");

        sellerInfoDao.save(sellerInfo);

        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public void testFindByOpenid() {
        SellerInfo sellerInfo = sellerInfoDao.findByOpenid("abc");
        log.info("sellerInfo = {}", sellerInfo);
        Assert.assertNotNull(sellerInfo);
    }

}