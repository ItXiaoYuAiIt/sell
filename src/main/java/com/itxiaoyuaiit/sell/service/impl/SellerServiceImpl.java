package com.itxiaoyuaiit.sell.service.impl;

import com.itxiaoyuaiit.sell.dataobject.SellerInfo;
import com.itxiaoyuaiit.sell.repository.SellerInfoRepository;
import com.itxiaoyuaiit.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Des
 * @Author wuyuqing
 * @Date 2020/9/1 15:58
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
