package com.zhzhgang.order.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 商品详情
 * Created by zhangzhonggang
 * 2017-10-06 0:39
 */
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private String productPrice;

    @JsonProperty("description")
    private String productDesc;

    @JsonProperty("icon")
    private String productIcon;
}
