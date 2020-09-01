package com.itxiaoyuaiit.sell;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JasyptTest {

    @Autowired
    private StringEncryptor encryptor;


    @Test
    public void getPass() {
        String name = "******";
        String password = "******";
        log.info("name: " + name + " ,password: " + password);
        log.info("name: {}, password: {}", encryptor.encrypt(name), encryptor.encrypt(password));

    }
}
