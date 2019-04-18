package com.grady.mychat.util;

import com.grady.mychat.constant.WeChatConstants;
import com.grady.mychat.message.*;
import com.grady.mychat.message.bean.Article;
import com.grady.mychat.model.WechatSettings;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mychat
 * @description: wechat xml utils
 * @author: luchangjiang
 * @create: 2019-03-04 19:57
 **/
public class MessageUtil {
    public static Map<String, String> parseXml(InputStream stream){
        Map<String, String> map= new HashMap<>();
        SAXReader reader=new SAXReader();
        try {
            Document doc= reader.read(stream);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for (Element element: elements) {
                map.put(element.getName(), element.getStringValue());
            }
        }
        catch (DocumentException e){
            e.printStackTrace();
        }
        return map;
    }

    public static String getResponse(Map<String, String> map){
        BaseMessage baseMessage=null;
        String msgType= map.get("MsgType");
        switch (msgType){
            case "text":
                if("图文".equalsIgnoreCase(map.get("Content").toString())){
                    List<Article> articles = new ArrayList<Article>();
                    Article article=new Article();
                    article.setTitle("来自帅哥的消息");
                    article.setDescription("这是一个来自帅哥的好消息");
                    article.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/ic6ICLhGJwBYRg33qiaQ27TNhZylyyCPDbqBfJovkY7EXq0cDgZFzAJDJw2bicTEl8Y7tUicAfs1zUZqZVLZlDp1qg/0");
                    article.setUrl("baidu.com");
                    articles.add(article);
                    baseMessage = new ArticleMessage(map, articles);
                }
                else if("登录".equalsIgnoreCase(map.get("Content").toString())){
                    String uri= WeChatConstants.AUTH_CODE_URL.replace("APPID", WechatSettings.appId)
                            .replace("REDIRECT_URI", WeChatConstants.BASE_URL + "/auth/uncoCallback")
                            .replace("SCOPE","snsapi_userinfo");
                    baseMessage = new TextMessage(map, "请点击<a href=\""+ uri +"\">这里</a>登录！");
                }
                else { baseMessage = new TextMessage(map, "你好帅！");
                }
                break;
            case "image":
                /*ImageMessage.ImageItem imageItem =new ImageMessage.ImageItem("mediaId");
                baseMessage = new ImageMessage(map, imageItem);*/
                break;
            case "voice":
                break;
            case "video":
                break;
            case "music":
                break;
            case "news":

                break;
            default:
                break;
        }
        String xml = beanToXml(baseMessage);
        System.out.println(xml);
        return xml;
    }

    private static String beanToXml(BaseMessage baseMessage){
        if(null == baseMessage)
            return null;

        XStream stream= new XStream();
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(ArticleMessage.class);

        String xml = stream.toXML(baseMessage);
        return xml;
    }
}
