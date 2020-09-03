package com.psq.train.activemq;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * ActiveStreamResources.java
 * Description: 正在发布视频资源实体类，对应xml文件
 *
 * @author Peng Shiquan
 * Copyright  2018-2019  创捷运维智能科技有限公司
 * All rights reserved.
 * @version: 1.0
 * Reversion:
 * 1.0 - 新建
 */
@XStreamAlias("Root")
public class ActiveStreamResources {

    @XStreamImplicit(itemFieldName = "Item")
    private List<StreamNode> streamNodes;

    public List<StreamNode> getStreamNodes() {
        return streamNodes;
    }

    public void setStreamNodes(List<StreamNode> streamNodes) {
        this.streamNodes = streamNodes;
    }

    @Override
    public String toString() {
        return "ActiveStreamResources{" +
                "streamNodes=" + streamNodes +
                '}';
    }
}
