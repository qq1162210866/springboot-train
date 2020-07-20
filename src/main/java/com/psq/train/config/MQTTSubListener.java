package com.psq.train.config;

import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttSubscribedEvent;
import org.springframework.stereotype.Component;

/**
 * MQTTSubListener.java
 * Description: MQTT订阅触发器
 *
 * @author Peng Shiquan
 * @date 2020/7/13
 */
@Component
public class MQTTSubListener implements ApplicationListener<MqttSubscribedEvent> {

    @Override
    public void onApplicationEvent(MqttSubscribedEvent event) {
        System.err.println("连接成功");
    }
}
