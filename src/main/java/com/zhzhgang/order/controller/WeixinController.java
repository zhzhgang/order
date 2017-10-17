package com.zhzhgang.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangzhonggang
 * 2017-10-17 14:39
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public void auth() {
        log.info("进入 auth 方法。。。");
    }
}
