package com.psq.train.controller;

import com.psq.train.dao.UserMapper;
import com.psq.train.mysql.SpringTransactionalTrain;
import com.psq.train.mysql.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TransactionalController.java
 * Description:  controller类，用于测试
 *
 * @author Peng Shiquan
 * @date 2020/6/13
 */
@RestController
public class TransactionalController {

    @Autowired
    private SpringTransactionalTrain springTransactionalTrain;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String saveTestUser() {
        springTransactionalTrain.mybatisTransactionalTrain();
        return "hello";
    }
}
