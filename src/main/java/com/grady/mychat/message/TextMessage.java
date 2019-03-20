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
public class TextMessage extends BaseMessage{
    @XStreamAlias("Content")
    private String content;

    public TextMessage(Map<String, String> map, String content){
        super(map);
        this.setMsgType("text");
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{} " + super.toString();
    }
}
