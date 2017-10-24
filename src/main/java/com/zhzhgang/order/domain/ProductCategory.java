package com.zhzhgang.order.domain;

import lombok.Data;

import java.util.Date;

/**
 * 商品类目实体类
 * Created by zhangzhonggang
 * 2017-10-04 14:19
 */
@Data
public class ProductCategory extends BaseEntity {

    /** 类目 id. */
    private Integer categoryId;

    /** 类目名称. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

    private Date ctime;

    private Date utime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
