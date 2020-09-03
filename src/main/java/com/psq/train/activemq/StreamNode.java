package com.psq.train.activemq;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * StreamNode.java
 * Description:
 *
 * @author Peng Shiquan
 * Copyright  2018-2019  创捷运维智能科技有限公司
 * All rights reserved.
 * @version: 1.0
 * Reversion:
 * 1.0 - 新建
 */
@XStreamAlias("Item")
public class StreamNode {

    /**
     * 摄像机名称
     */
    @XStreamAsAttribute
    @XStreamAlias("CamName")
    private String camName;

    /**
     * 摄像机编码
     */
    @XStreamAsAttribute
    @XStreamAlias("CamCode")
    private String camCode;

    /**
     * 是否在解码显示
     */
    @XStreamAsAttribute
    @XStreamAlias("Display")
    private String display;

    /**
     * 推送地址
     */
    @XStreamAsAttribute
    @XStreamAlias("URL")
    private String url;

    /**
     * 推送状态
     */
    @XStreamAsAttribute
    @XStreamAlias("Status")
    private String status;

    /**
     * 发布视频宽度
     */
    @XStreamAsAttribute
    @XStreamAlias("Width")
    private String width;

    /**
     * 发布视频高度
     */
    @XStreamAsAttribute
    @XStreamAlias("Height")
    private String height;

    /**
     * 发布视频码流率
     */
    @XStreamAsAttribute
    @XStreamAlias("BPS")
    private String bps;

    /**
     * 发布视频HLS地址
     */
    @XStreamAsAttribute
    @XStreamAlias("HLS")
    private String hls;

    public String getCamName() {
        return camName;
    }

    public void setCamName(String camName) {
        this.camName = camName;
    }

    public String getCamCode() {
        return camCode;
    }

    public void setCamCode(String camCode) {
        this.camCode = camCode;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBps() {
        return bps;
    }

    public void setBps(String bps) {
        this.bps = bps;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

    @Override
    public String toString() {
        return "StreamNode{" +
                "camName='" + camName + '\'' +
                ", camCode='" + camCode + '\'' +
                ", display='" + display + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", bps='" + bps + '\'' +
                ", hls='" + hls + '\'' +
                '}';
    }
}
