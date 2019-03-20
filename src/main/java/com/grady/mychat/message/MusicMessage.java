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
public class MusicMessage extends BaseMessage{
    private MusicItem musicItem;

    public MusicMessage(Map<String, String> map, MusicItem musicItem){
        super(map);
        this.setMsgType("music");
        this.musicItem = musicItem;
    }

    @Data
    private class MusicItem {
        private String title;
        private String description;
        private String musicUrl;
        private String hqMusicUrl;
        private String thumbMediaId;
    }
}
