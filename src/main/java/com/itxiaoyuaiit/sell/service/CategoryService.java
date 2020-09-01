package com.itxiaoyuaiit.sell.service;

import com.itxiaoyuaiit.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:58
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
