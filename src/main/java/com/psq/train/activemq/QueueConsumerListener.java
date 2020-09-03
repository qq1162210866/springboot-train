package com.psq.train.activemq;

import com.thoughtworks.xstream.XStream;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class QueueConsumerListener {

    private static final Logger logger = LoggerFactory.getLogger(QueueConsumerListener.class);
    @Autowired
    private Producer producer;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Description: 存活消息接收，需要接收到存活消息后再请求当前所有摄像机的列表
     *
     * @param message
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/9/1
     */
    @JmsListener(destination = "KeepLive")
    public void readActiveQueue(Message message) {
        if (message instanceof ActiveMQTextMessage) {
            ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
            try {
                String text = activeMQTextMessage.getText();
                String pushServerID = text.substring(text.indexOf("<ID>") + 4, text.indexOf("</ID>"));
                /**
                 * 发送请求当前推流服务所有摄像机列表
                 */
                CameraControl cameraControl = new CameraControl();
                cameraControl.setAct("QueryCameraList");
                cameraControl.setPushServerID(pushServerID);
                producer.send("Interactive", cameraControl, 4);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.error("错误的接收一条消息，消息为：" + message.toString());
        }
    }

    /**
     * Description: 接收摄像头资源消息，存入Redis
     *
     * @param message
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/9/2
     */
    @JmsListener(destination = "Interactive")
    public void readCameraListQueue(Message message) {
        if (message instanceof ActiveMQMapMessage) {
            ActiveMQMapMessage cameraListMessage = (ActiveMQMapMessage) message;
            try {
                String xml = cameraListMessage.getContentMap().get("CamList").toString();
                String id = cameraListMessage.getContentMap().get("ID").toString();
                XStream xstream = new XStream();
                XStream.setupDefaultSecurity(xstream);
                xstream.processAnnotations(CameraResources.class);
                xstream.autodetectAnnotations(true);
                CameraResources cameraResources = (CameraResources) xstream.fromXML(xml);
                if (cameraResources == null) {
                    logger.error("解析失败，失败消息为：" + xml);
                    return;
                }
                String key = "Interactive:" + id;
                String value = "";
                for (CameraNode cameraNode : cameraResources.getCamerList()) {
                    value += cameraNode.getCode() + ",";
                }
                value = value.substring(0, value.length() - 1);
                stringRedisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);
            } catch (Exception e) {
                logger.error(e.toString());
            }
        } else {

        }
    }

    /**
     * Description: 请求视频发布列表、请求发布视频都使用这个接口
     * 回复摄像头资源消息接收，接收后需要判断所有设备是否正常转流，没有转流的需要激活
     * 请求发布视频，接收信息后存入redis
     *
     * @param message
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/9/1
     */
    @JmsListener(destination = "StreamPush")
    public void readActiveQueue1(Message message) {
        if (message instanceof ActiveMQMapMessage) {
            ActiveMQMapMessage activeMQMapMessage = (ActiveMQMapMessage) message;
            String msgType = null;
            try {
                msgType = activeMQMapMessage.getProperty("MsgType").toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (msgType != null && "13".equals(msgType)) {
                String xml = null;
                String id = null;
                try {
                    xml = activeMQMapMessage.getContentMap().get("ActiveStream").toString();
                    id = activeMQMapMessage.getContentMap().get("ID").toString();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                //转换成xml文件、取所有设备，对照信息。在发送未转流的。
                XStream xstream = new XStream();
                xstream.processAnnotations(ActiveStreamResources.class);
                XStream.setupDefaultSecurity(xstream);
                xstream.autodetectAnnotations(true);
                ActiveStreamResources activeStreamResources = (ActiveStreamResources) xstream.fromXML(xml);
                if (activeStreamResources == null) {
                    logger.error("解析失败，失败消息为：" + xml);
                    return;
                }
                String key = "Interactive:" + id;
                String codes = stringRedisTemplate.opsForValue().get(key);
                List<String> codeList = Arrays.asList(codes.split(",").clone());
                List<String> sendCodeList = new ArrayList<>(codeList);
                for (StreamNode streamNode : activeStreamResources.getStreamNodes()) {
                    String streamKey = "Stream:" + streamNode.getCamCode();
                    String value = streamNode.getUrl();
                    stringRedisTemplate.opsForValue().set(streamKey, value, 30, TimeUnit.SECONDS);
                    if (sendCodeList.contains(streamNode.getCamCode())) {
                        sendCodeList.remove(streamNode.getCamCode());
                    }
                }
                /**
                 * 发送转流消息
                 */
                sendPushMessage(codeList, id);
            }
        } else if (message instanceof ActiveMQTextMessage) {
            ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
            String msgType = null;
            try {
                msgType = activeMQTextMessage.getProperty("MsgType").toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (msgType != null && "11".equals(msgType)) {
                try {
                    String text = activeMQTextMessage.getText();
                    String code = text.substring(text.indexOf("<Code>") + 6, text.indexOf("</Code>"));
                    String camID = text.substring(text.indexOf("<CamID>") + 7, text.indexOf("</CamID>"));
                    String pubName = text.substring(text.indexOf("<PubName>") + 9, text.indexOf("</PubName>"));
                    if ("0".equals(code)) {
                        String key = "Stream:" + camID;
                        String value = "192.168.1.183:1935/" + pubName;
                        stringRedisTemplate.opsForValue().set(key, value, 30, TimeUnit.SECONDS);
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        } else {
            /**
             * 会接收到自己发送的消息，后面可以做日志记录，但是现在不需要记录
             */
        }
    }

    /**
     * Description: 发送推流信息
     *
     * @param codeList
     * @param pushServerID
     * @return void
     * @Author: Peng Shiquan
     * @Date: 2020/9/3
     */
    public void sendPushMessage(List<String> codeList, String pushServerID) {
        /**
         * 暂时这样，后续需要完善
         */
        for (String code : codeList) {
            CameraControl cameraControl = new CameraControl();
            cameraControl.setAct("PushStream");
            cameraControl.setCamID(code);
            cameraControl.setStreamType("1");
            cameraControl.setSolution("6");
            cameraControl.setRTMPServer("192.168.1.183");
            cameraControl.setRTMPPort("1935");
            cameraControl.setUser("admin");
            cameraControl.setPwd("hik12345");
            cameraControl.setPubName("rtmp/" + code);
            cameraControl.setWidth("960");
            cameraControl.setHeight("528");
            cameraControl.setBPS("1039");
            cameraControl.setIFrameInterval("25");
            cameraControl.setFPS("40");
            cameraControl.setPushServerID(pushServerID);
            try {
                producer.send("StreamPush", cameraControl, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
