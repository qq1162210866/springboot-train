package com.psq.train.controller;

import com.psq.train.config.MQTTConfig;
import com.psq.train.mysql.SpringTransactionalTrain;
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
    @Autowired
    private MQTTConfig.MyGateway myGateway;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String saveTestUser() {
        springTransactionalTrain.mybatisTransactionalTrain();
        return "hello";
    }

    @RequestMapping(value = "/mqtt", method = RequestMethod.GET)
    public String sendMQTTMsg() {
        myGateway.sendToMqtt("testTopic1", "hello1");
        myGateway.sendToMqtt("testTopic2", "hello2");
        return "hello";
    }
}
