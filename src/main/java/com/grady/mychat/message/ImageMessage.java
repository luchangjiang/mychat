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
public class ImageMessage extends BaseMessage{
    private ImageItem imageItem;

    public ImageMessage(Map<String, String> map, ImageItem imageItem){
        super(map);
        this.setMsgType("image");
        this.imageItem = imageItem;
    }

    @Data
    public class ImageItem{
        private String mediaId;

        public ImageItem(String mediaId) {
            this.mediaId = mediaId;
        }
    }
}
