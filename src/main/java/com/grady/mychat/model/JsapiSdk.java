package com.grady.mychat.model;

import lombok.Data;

/**
 * @program: mychat
 * @description: jsapi ticket
 * @author: luchangjiang
 * @create: 2019-03-20 20:51
 **/
@Data
public class JsapiSdk {
    private String jsapi_ticket;
    private String noncestr;
    private String timestamp;
    private String url;
    private String signature;

    private long expireTime;

    public JsapiSdk(String jsapi_ticket, String noncestr, String url, String signature, String expireIn) {
        this.jsapi_ticket = jsapi_ticket;
        this.noncestr = noncestr;
        long timestamp = System.currentTimeMillis();
        this.timestamp = String.valueOf( timestamp / 1000);
        this.url = url;
        this.signature = signature;
        this.expireTime = timestamp+Integer.parseInt(expireIn)*1000;
    }

    /**
     * 判断AccessToken是否过期
     * @return
     */
    public Boolean isExpired(){
        return System.currentTimeMillis() > this.expireTime;
    }
}
