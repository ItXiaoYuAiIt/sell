package com.itxiaoyuaiit.sell.form;

import lombok.Data;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:56
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
