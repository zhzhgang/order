package com.zhzhgang.order.service.impl;

import com.zhzhgang.order.domain.SellerInfo;
import com.zhzhgang.order.service.SellerService;
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
 * 2017-12-21 17:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {

    @Autowired
    private SellerService sellerService;

    @Test
    public void testFindSellerInfoByOpenid() throws Exception {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid("abc");
        log.info("sellerInfo = {}", sellerInfo);
        Assert.assertNotNull(sellerInfo);
    }

}