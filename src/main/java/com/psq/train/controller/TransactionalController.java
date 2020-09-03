package com.psq.train.controller;

import com.psq.train.config.MQTTConfig;
import com.psq.train.mysql.SpringTransactionalTrain;
import com.psq.train.repository.TestPerson;
import com.psq.train.repository.TestPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private TestPersonRepository testPersonRepository;


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

    @RequestMapping(value = "/elasticsearch", method = RequestMethod.GET)
    public String testES() {
        TestPerson testPerson = new TestPerson();
        testPerson.setFirstName("psq");
        testPerson.setLastName("haha");
        TestPerson saveResult = testPersonRepository.save(testPerson);
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<TestPerson> testPersonPage = testPersonRepository.findAll(pageRequest);
        return testPersonPage.toString();
    }
}
