package com.grady.mychat.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

@Data
@XStreamAlias("xml")
public class BaseMessage {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;

    public BaseMessage(Map<String, String> requestMap){
        this.fromUserName = requestMap.get("ToUserName");
        this.toUserName = requestMap.get("FromUserName");
        this.msgType = requestMap.get("MsgType");
        this.createTime=System.currentTimeMillis()/1000+"";
    }
}
