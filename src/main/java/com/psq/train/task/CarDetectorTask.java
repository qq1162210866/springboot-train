package com.psq.train.task;


import com.psq.train.activemq.CameraControl;
import com.psq.train.activemq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * CarDetectorTask.java
 * Description: 定时任务，定时发送ActiveMQ消息
 *
 * @author Peng Shiquan
 * @date 2020/9/3
 */
@Component
@EnableScheduling
public class CarDetectorTask {
    private static final Logger logger = LoggerFactory.getLogger(CarDetectorTask.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Producer producer;

    /**
     * 启动时执行一次，之后每隔60分钟执行一次
     */
    @Scheduled(fixedDelay = 1000 * 30, initialDelay = 1000)
    public void test() {
        /**
         * redis中读取PushServerID,发送消息获取当前存在的流
         */
        Set<String> keys = stringRedisTemplate.keys("Interactive:*");
        for (String s : keys) {
            CameraControl cameraControl = new CameraControl();
            cameraControl.setAct("QueryPublishCamList");
            cameraControl.setPushServerID(s.replaceAll("Interactive:", ""));
            try {
                producer.send("StreamPush", cameraControl, 12);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}

