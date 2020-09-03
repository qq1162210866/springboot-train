package com.psq.train.activemq;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * CameraNode.java
 * Description: 摄像机节点，xml解析使用实体类
 *
 * @author Peng Shiquan
 * Copyright  2018-2019  创捷运维智能科技有限公司
 * All rights reserved.
 * @version: 1.0
 * Reversion:
 * 1.0 - 新建
 */
@XStreamAlias("Item")
public class CameraNode {

    /**
     * 摄像头唯一标识
     */
    @XStreamAsAttribute
    @XStreamAlias("Code")
    private String code;
    /**
     * 摄像头名称
     */
    @XStreamAsAttribute
    @XStreamAlias("Name")
    private String name;
    /**
     * 在线状态
     */
    @XStreamAsAttribute
    @XStreamAlias("Status")
    private String status;
    /**
     * CMS中唯一设备标识
     */
    @XStreamAsAttribute
    @XStreamAlias("Device")
    private String device;
    /**
     * 通道号
     */
    @XStreamAsAttribute
    @XStreamAlias("Chn")
    private String chn;
    /**
     * GPS信息
     */
    @XStreamAsAttribute
    @XStreamAlias("GPS")
    private String gps;
    /**
     * 推送地址
     */
    @XStreamAsAttribute
    @XStreamAlias("ContURL")
    private String conturl;
    /**
     * 所属组织结构ID
     */
    @XStreamAsAttribute
    @XStreamAlias("OrganizationID")
    private String organizationid;
    /**
     * 主/子码流标识
     */
    @XStreamAsAttribute
    @XStreamAlias("StreamType")
    private String streamType;
    /**
     * 源服务器CMS
     */
    @XStreamAsAttribute
    @XStreamAlias("CMSSrc")
    private String cMSSrc;
    /**
     * 视频发布HLS地址
     */
    @XStreamAsAttribute
    @XStreamAlias("HLS")
    private String hls;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getChn() {
        return chn;
    }

    public void setChn(String chn) {
        this.chn = chn;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getConturl() {
        return conturl;
    }

    public void setConturl(String conturl) {
        this.conturl = conturl;
    }

    public String getOrganizationid() {
        return organizationid;
    }

    public void setOrganizationid(String organizationid) {
        this.organizationid = organizationid;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public String getCMSSrc() {
        return cMSSrc;
    }

    public void setCMSSrc(String CMSSrc) {
        this.cMSSrc = CMSSrc;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

    @Override
    public String toString() {
        return "CameraNode{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", device='" + device + '\'' +
                ", chn='" + chn + '\'' +
                ", gps='" + gps + '\'' +
                ", conturl='" + conturl + '\'' +
                ", organizationid='" + organizationid + '\'' +
                ", streamType='" + streamType + '\'' +
                ", CMSSrc='" + cMSSrc + '\'' +
                ", hls='" + hls + '\'' +
                '}';
    }
}
