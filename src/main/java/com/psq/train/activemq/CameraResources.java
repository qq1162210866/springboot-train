package com.psq.train.activemq;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * CameraResources.java
 * Description: 转流服务设备状态返回xml对应的实体类
 *
 * @author Peng Shiquan
 * Copyright  2018-2019  创捷运维智能科技有限公司
 * All rights reserved.
 * @version: 1.0
 * Reversion:
 * 1.0 - 新建
 */
@XStreamAlias("Root")
public class CameraResources {
    @XStreamImplicit(itemFieldName = "Item")
    private List<CameraNode> cameraList;

    public List<CameraNode> getCamerList() {
        return cameraList;
    }

    public void setCamerList(List<CameraNode> cameraList) {
        this.cameraList = cameraList;
    }

    @Override
    public String toString() {
        return "CameraResources{" +
                "cameraList=" + cameraList +
                '}';
    }
}
