package com.grady.mychat.message.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @program: mychat
 * @description: article item message
 * @author: luchangjiang
 * @create: 2019-03-05 06:20
 **/
@Data
@XStreamAlias("item")
public class Article {
    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;
    @XStreamAlias("PicUrl")
    private String picUrl;
    @XStreamAlias("Url")
    private String url;
}
