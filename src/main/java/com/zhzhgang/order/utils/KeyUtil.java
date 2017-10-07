package com.zhzhgang.order.utils;

import java.util.Random;

/**
 * Created by zhangzhonggang
 * 2017-10-07 16:27
 */
public class KeyUtil {

    /**
     * 唯一键生成器
     * 格式：时间 + 随机数
     * @return
     */
    public synchronized static String genUniqueKey() {

        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

}
