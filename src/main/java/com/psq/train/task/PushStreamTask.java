package com.psq.train.task;

import com.alibaba.fastjson.JSONObject;
import com.psq.train.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * PushStreamTask.java
 * Description:
 *
 * @author Peng Shiquan
 * Copyright  2018-2019  创捷运维智能科技有限公司
 * All rights reserved.
 * @version: 1.0
 * Reversion:
 * 1.0 - 新建
 */
@Component
@EnableScheduling
public class PushStreamTask {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 启动时执行一次，之后每隔60分钟执行一次
     */
    @Scheduled(fixedDelay = 1000 * 5, initialDelay = 1000)
    public void stopPushStream() {
        Map<String, Long> pushMap = redisTemplate.opsForHash().entries("pushStream");
        for (String key : pushMap.keySet()) {
            Long nowTime = System.currentTimeMillis();
            Long oldTime = pushMap.get(key);
            if (nowTime > oldTime) {
                /**
                 * 发送停止推流的请求
                 */
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("sipAction", "video.VideoTransFinish");
                jsonObject.put("cameraNum", key);
                jsonObject.put("user", "");
                jsonObject.put("videoType", 0);
                Map<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json");
                String result = HttpUtil.doPost("http://192.168.1.148:5556/service", jsonObject.toJSONString(), header);
                redisTemplate.opsForHash().delete("pushStream", key);
                System.err.println("删除成功，结果为:" + result);
            }
        }
    }

    @Scheduled(fixedDelay = 1000 * 7, initialDelay = 1000 * 6)
    public void checkPushStream() {
        Map<String, Long> pushMap = redisTemplate.opsForHash().entries("pushStream");
        /**
         * 获取当前正在推送的流
         */
        for (String key : pushMap.keySet()) {
            //检查
            if (true) {

            }
        }
    }
}
