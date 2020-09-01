package com.itxiaoyuaiit.sell.service;

import com.itxiaoyuaiit.sell.dataobject.SellerInfo;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:59
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
