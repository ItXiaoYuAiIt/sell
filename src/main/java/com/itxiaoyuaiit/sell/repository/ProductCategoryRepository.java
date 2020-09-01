package com.itxiaoyuaiit.sell.repository;

import com.itxiaoyuaiit.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:57
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
