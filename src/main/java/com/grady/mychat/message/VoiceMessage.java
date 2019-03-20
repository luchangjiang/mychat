package com.grady.mychat.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

/**
 * @program: mychat
 * @description: wechat text message
 * @author: luchangjiang
 * @create: 2019-03-04 20:55
 **/
@Data
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{
    private VoiceItem voiceItem;

    public VoiceMessage(Map<String, String> map, VoiceItem voiceItem){
        super(map);
        this.setMsgType("voice");
        this.voiceItem = voiceItem;
    }

    @Data
    private class VoiceItem{
        private String mediaId;
    }
}
