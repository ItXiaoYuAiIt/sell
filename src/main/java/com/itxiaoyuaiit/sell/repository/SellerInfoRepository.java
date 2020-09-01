package com.itxiaoyuaiit.sell.repository;

import com.itxiaoyuaiit.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:57
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
