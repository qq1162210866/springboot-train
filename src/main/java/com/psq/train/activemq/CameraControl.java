package com.psq.train.activemq;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CameraControl.java
 * Description: 摄像机转流服务的命令实体类
 *
 * @author Peng Shiquan
 * Copyright  2018-2019  创捷运维智能科技有限公司
 * All rights reserved.
 * @version: 1.0
 * Reversion:
 * 1.0 - 新建
 */
public class CameraControl {

    /**
     * 动作标识
     */
    @XStreamAsAttribute
    @XStreamAlias("Act")
    private String Act;

    /**
     * 推送服务器ID
     */
    @XStreamAsAttribute
    @XStreamAlias("PushServerID")
    private String PushServerID;

    /**
     * 摄像机标识
     */
    @XStreamAsAttribute
    @XStreamAlias("CamID")
    private String CamID;

    /**
     * 主/子码流标识
     */
    @XStreamAsAttribute
    @XStreamAlias("StreamType")
    private String StreamType;
    /**
     * 转码方案
     */
    @XStreamAsAttribute
    @XStreamAlias("Solution")
    private String Solution;
    /**
     * RTMP服务器地址
     */
    @XStreamAsAttribute
    @XStreamAlias("RTMPServer")
    private String RTMPServer;
    /**
     * RTMP服务器的端口
     */
    @XStreamAsAttribute
    @XStreamAlias("RTMPPort")
    private String RTMPPort;
    /**
     * 推流鉴权的用户名
     */
    @XStreamAsAttribute
    @XStreamAlias("User")
    private String User;

    /**
     * 鉴权密码
     */
    @XStreamAsAttribute
    @XStreamAlias("Pwd")
    private String Pwd;
    /**
     * 视频发布的名称
     */
    @XStreamAsAttribute
    @XStreamAlias("PubName")
    private String PubName;
    /**
     * 转码宽度
     */
    @XStreamAsAttribute
    @XStreamAlias("Width")
    private String Width;
    /**
     * 转码高度
     */
    @XStreamAsAttribute
    @XStreamAlias("Height")
    private String Height;
    /**
     * 转码后的码流
     */
    @XStreamAsAttribute
    @XStreamAlias("BPS")
    private String BPS;
    /**
     * 关键帧间隔
     */
    @XStreamAsAttribute
    @XStreamAlias("IFrameInterval")
    private String IFrameInterval;
    /**
     * 转码帧率
     */
    @XStreamAsAttribute
    @XStreamAlias("FPS")
    private String FPS;

    public String getAct() {
        return Act;
    }

    public void setAct(String act) {
        Act = act;
    }

    public String getPushServerID() {
        return PushServerID;
    }

    public void setPushServerID(String pushServerID) {
        PushServerID = pushServerID;
    }

    public String getCamID() {
        return CamID;
    }

    public void setCamID(String camID) {
        CamID = camID;
    }

    public String getStreamType() {
        return StreamType;
    }

    public void setStreamType(String streamType) {
        StreamType = streamType;
    }

    public String getSolution() {
        return Solution;
    }

    public void setSolution(String solution) {
        Solution = solution;
    }

    public String getRTMPServer() {
        return RTMPServer;
    }

    public void setRTMPServer(String RTMPServer) {
        this.RTMPServer = RTMPServer;
    }

    public String getRTMPPort() {
        return RTMPPort;
    }

    public void setRTMPPort(String RTMPPort) {
        this.RTMPPort = RTMPPort;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getPubName() {
        return PubName;
    }

    public void setPubName(String pubName) {
        PubName = pubName;
    }

    public String getWidth() {
        return Width;
    }

    public void setWidth(String width) {
        Width = width;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getBPS() {
        return BPS;
    }

    public void setBPS(String BPS) {
        this.BPS = BPS;
    }

    public String getIFrameInterval() {
        return IFrameInterval;
    }

    public void setIFrameInterval(String IFrameInterval) {
        this.IFrameInterval = IFrameInterval;
    }

    public String getFPS() {
        return FPS;
    }

    public void setFPS(String FPS) {
        this.FPS = FPS;
    }

    @Override
    public String toString() {
        XStream xstream = new XStream();
        xstream.alias("", CameraControl.class);
        XStream.setupDefaultSecurity(xstream);
        String text = xstream.toXML(this);
        text = text.replaceAll("<>", "").replaceAll("</>", "");
        Pattern p = Pattern.compile("\\s{2,}|\t|\r|\n");
        Matcher m = p.matcher(text);
        String finalresult = m.replaceAll("");
        return finalresult;
    }
}
