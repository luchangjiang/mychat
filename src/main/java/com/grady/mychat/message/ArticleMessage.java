package com.grady.mychat.message;

import com.grady.mychat.message.bean.Article;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @program: mychat
 * @description: wechat text message
 * @author: luchangjiang
 * @create: 2019-03-04 20:55
 **/
@Data
@XStreamAlias("xml")
public class ArticleMessage extends BaseMessage{
    @XStreamAlias("ArticleCount")
    private String articleCount;
    @XStreamAlias("Articles")
    private List<Article> articles;

    public ArticleMessage(Map<String, String> map, List<Article> articles){
        super(map);
        this.setMsgType("news");
        this.articles = articles;
        this.articleCount = String.valueOf(articles.size());
    }
}
