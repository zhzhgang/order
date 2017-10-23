package com.zhzhgang.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhzhgang.order.enums.ProductStatusEnum;
import com.zhzhgang.order.utils.EnumUtil;
import lombok.Data;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息实体类
 * Created by zhangzhonggang
 * 2017-10-05 15:48
 */
@Data
public class ProductInfo extends BaseEntity {

    private String productId;

    /** 商品名称. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDesc;

    /** 小图. */
    private String productIcon;

    /** 状态，0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;

    private Date ctime;

    private Date utime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
