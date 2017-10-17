package com.zhzhgang.order.utils;

import com.zhzhgang.order.enums.CodeEnum;

/**
 * Created by zhangzhonggang
 * 2017-10-17 19:57
 */
public class EnumUtil {

    public static <T extends CodeEnum> String getMsgByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each.getMsg();
            }
        }
        return "";
    }
}
