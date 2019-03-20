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
public class VideoMessage extends BaseMessage{
    private VideoItem videoItem;

    public VideoMessage(Map<String, String> map, VideoItem videoItem){
        super(map);
        this.setMsgType("video");
        this.videoItem = videoItem;
    }

    @Data
    private class VideoItem {
        private String mediaId;
        private String title;
        private String description;
    }
}
