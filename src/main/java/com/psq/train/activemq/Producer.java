package com.psq.train.activemq;


import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Producer.java
 * Description: ActiveMQ发送方法
 *
 * @author Peng Shiquan
 * Copyright  2018-2019  创捷运维智能科技有限公司
 * All rights reserved.
 * @version: 1.0
 * Reversion:
 * 1.0 - 新建
 */
@Component
public class Producer {
    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * Description: 发送转流消息到ActiveMQ
     *
     * @param destination  目的地（topic）
     * @param cameraControl 控制命令对象
     * @param msgType      消息类型
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/9/1
     */
    public void send(String destination, CameraControl cameraControl, Integer msgType) throws Exception {
        ActiveMQTextMessage activeMQTextMessage = new ActiveMQTextMessage();
        activeMQTextMessage.setProperty("MsgType", msgType);
        activeMQTextMessage.setText(cameraControl.toString());
        jmsTemplate.send(destination, (session) -> activeMQTextMessage);
    }
}
