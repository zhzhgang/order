package com.zhzhgang.order.utils;

import com.zhzhgang.order.VO.ResultVO;

/**
 * Created by zhangzhonggang
 * 2017-10-06 15:58
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);

        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg("msg");

        return resultVO;
    }
}
