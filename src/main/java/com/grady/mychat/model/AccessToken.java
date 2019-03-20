package com.grady.mychat.model;

import lombok.Data;

/**
 * @program: mychat
 * @description: wechat access token
 * @author: luchangjiang
 * @create: 2019-03-05 09:39
 **/
@Data
public class AccessToken {
    private String accessToken;
    private long expireTime;

    public AccessToken(String accessToken, String expireIn) {
        this.accessToken = accessToken;
        this.expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
    }

    /**
     * 判断AccessToken是否过期
     * @return
     */
    public Boolean isExpired(){
        return System.currentTimeMillis() > this.expireTime;
    }
}
