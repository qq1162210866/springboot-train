package com.psq.train.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.psq.train.util.HttpUtil;
import com.psq.train.util.RequestPushDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * HttpPushStreamController.java
 * Description: 推流测试类
 *
 * @author Peng Shiquan
 * @date 2020/11/10
 */
@RestController
public class HttpPushStreamController {


    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/beginPush", method = RequestMethod.GET)
    public String beginPush() {
        //查询数据库，目前可以直接将所有视频都推送
        String result = null;
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        String send = "{\n" +
                "    \"sipAction\":\"catalog\"\n" +
                "}";
        result = HttpUtil.doPost("http://192.168.1.148:5556/service", send, header);
        return result;
    }

    private static final String URL = "http://192.168.1.148:5556/service";
    private static final String SIPACTION = "video.VideoTransBegin";
    private static final String USER = "";
    private static final int VIDEOTYPE = 0;
    private static final String SERVERIP = "192.168.1.148";
    private static final int SERVERPORT = 1935;
    private static final String PUBNAME = "/live/rtmp";
    private static final String AUTHINFO = "";
    private static final int WIDTH = 352;
    private static final int HEIGHT = 288;
    private static final int BITRATE = 200;
    private static final int IFRAME = 50;
    private static final int FRAMERATE = 15;


    /**
     * Description: EISC请求推流的接口
     *
     * @param cameraID
     * @param time
     * @return java.lang.String
     * @Author: Peng Shiquan
     * @Date: 2020/11/10
     */
    @RequestMapping(value = "/requestPush", method = RequestMethod.GET)
    public String requestPush(String cameraID, Integer time) {
        RequestPushDTO requestPushDTO = new RequestPushDTO();
        requestPushDTO.setSipAction(SIPACTION);
        requestPushDTO.setCameraNum(cameraID);
        requestPushDTO.setUser(USER);
        requestPushDTO.setVideoType(VIDEOTYPE);
        requestPushDTO.setCommand(
                new RequestPushDTO.Command(SERVERIP, SERVERPORT, PUBNAME, AUTHINFO,
                        new RequestPushDTO.Video(WIDTH, HEIGHT, BITRATE, IFRAME, FRAMERATE)));
        String sendJson = JSON.toJSONString(requestPushDTO);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        String result = HttpUtil.doPost(URL, sendJson, header);
        JSONObject jsonObject = JSON.parseObject(result);
        String pushResult = (String) jsonObject.get("code");
        if ("0".equals(pushResult)) {
            Long nowTime = System.currentTimeMillis();
            redisTemplate.opsForHash().put("pushStream", cameraID, nowTime + time * 1000);
            return "success";
        } else {
        }
        return result;
    }

}
