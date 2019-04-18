package com.grady.mychat.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class WechatSettings {
    public static String appId;
    public static String appSecret;
    public static String appToken;

    @Value("${wechat.appId}")
    public void setAppId(String appId){
        WechatSettings.appId = appId;
    }

    @Value("${wechat.appSecret}")
    public void setAppSecret(String appSecret){
        WechatSettings.appSecret = appSecret;
    }

    @Value("${wechat.appToken}")
    public void setAppToken(String appToken){
        WechatSettings.appToken = appToken;
    }

}
