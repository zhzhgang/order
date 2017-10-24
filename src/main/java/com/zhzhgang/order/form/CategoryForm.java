package com.zhzhgang.order.form;

import lombok.Data;

/**
 * Created by zhangzhonggang
 * 2017-10-24 15:43
 */
@Data
public class CategoryForm {

    /** 类目 id. */
    private Integer categoryId;

    /** 类目名称. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
