package com.grady.mychat.util;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.grady.mychat.common.msg.RestResponse;
import com.grady.mychat.constant.WeChatConstants;
import com.grady.mychat.model.AccessToken;
import com.grady.mychat.model.JsapiSdk;
import com.grady.mychat.model.WeiXinUser;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class WeChatUtil {
    /**
     *
     * @return
     */
    public static void refreshAccessToken(){
        WebClient webClient = WebClient.create();
        Mono<String> resp = webClient
                .get().uri(WeChatConstants.getAccessTokenUrl())
                .retrieve()
                .bodyToMono(String.class);
        JSONObject jsonObject = JSONObject.parseObject(resp.block());

        System.out.println(jsonObject.toJSONString());
        String token=jsonObject.getString("access_token");
        String expireIn =jsonObject.getString("expires_in");
        AccessToken accessToken=new AccessToken(token, expireIn);
        System.out.println(accessToken);
        WeChatConstants.accessToken = accessToken;
    }

    public static AccessToken getAccessToken(){
        if(WeChatConstants.accessToken ==null|| WeChatConstants.accessToken.isExpired()){
            refreshAccessToken();
        }
        return WeChatConstants.accessToken;
    }

    public static RestResponse getQrCodeTicket(String message){
        String uri = WeChatUtil.replaceAccessToken(WeChatConstants.QR_CODE_TICKET_URL);

        Mono<String> resp= WebClient.create()
                .post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(message))
                .retrieve()
                .bodyToMono(String.class);

        RestResponse response = new RestResponse().rel(true);
        response.setData(JSONObject.parseObject(resp.block()));
        return response;
    }
    public static void refreshJsapiTicket(){
        String uri = WeChatUtil.replaceAccessToken(WeChatConstants.JSAPI_TICKET_URL);

        WebClient webClient = WebClient.create();
        Mono<String> resp = webClient
                .get().uri(uri)
                .retrieve()
                .bodyToMono(String.class);
        JSONObject jsonObject = JSONObject.parseObject(resp.block());

        System.out.println(jsonObject.toJSONString());
        String ticket=jsonObject.getString("ticket");
        String expireIn =jsonObject.getString("expires_in");
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        String url = WeChatConstants.baseUrl + "/mychat/jssdk";
        String signature =WeChatUtil.getSignature(ticket, nonceStr, timeStamp, url);

        JsapiSdk jsapiTicket=new JsapiSdk(ticket, nonceStr, url, signature, expireIn);
        System.out.println(jsapiTicket);
        WeChatConstants.jsapiTicket = jsapiTicket;
    }

    public static JsapiSdk getJsapiTicket(){
        if(WeChatConstants.jsapiTicket ==null|| WeChatConstants.jsapiTicket.isExpired()){
            refreshJsapiTicket();
        }
        return WeChatConstants.jsapiTicket;
    }

    public static String getUserByCode(String code){
        String tokenUri= WeChatConstants.AUTH_ACCESS_TOKEN_URL.replace("APPID", WeChatConstants.appId)
                .replace("SECRET", WeChatConstants.appSecret)
                .replace("CODE",code);
        String accessTokenObj = WebClient.create()
                .get().uri(tokenUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject jsonObject=JSONObject.parseObject(accessTokenObj);
        String authToken=jsonObject.getString("access_token");
        String openid=jsonObject.getString("openid");

        String userUri= WeChatConstants.AUTH_USER_INFO.replace("ACCESS_TOKEN",authToken)
                .replace("OPENID",openid);
        String user = WebClient.create()
                .get().uri(userUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(user);
        return user;
    }

    public static WeiXinUser getUserInfo(String openId){
        String uri = WeChatUtil.replaceAccessToken(WeChatConstants.USER_INFO_URL).replace("OPENID", openId);

        WeiXinUser resp= WebClient.create()
                .get().uri(uri)
                .retrieve()
                .bodyToMono(WeiXinUser.class)
                .block();

        return resp;
    }

    public static Boolean checkSignagure(String timestamp, String nonce, String signature){
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] arr=new String[]{WeChatConstants.token, timestamp,nonce};
        Arrays.sort(arr);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String plainText = arr[0]+arr[1]+arr[2];
        String mySig = sha1(plainText);
        System.out.println(mySig);
        System.out.println(signature);

        //3）开发者获得加密后的字符串可与signature对比
        return mySig.equalsIgnoreCase(signature);
    }

    public static String sha1(String plainText){
        String result=null;
        try {
            MessageDigest sha = MessageDigest.getInstance("sha1");
            byte[] digest = sha.digest(plainText.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuilder sb = new StringBuilder();
            for (byte d : digest) {
                sb.append(chars[(d >> 4) & 15]);
                sb.append(chars[d & 15]);
            }
            result = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return result;
    }

    public static String getSignature(String jsapi_ticket, String noncestr, String timestamp, String url){
        String str = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
        return sha1(str);
    }

    public static String replaceAccessToken(String uri){
        return uri.replace("ACCESS_TOKEN", WeChatUtil.getAccessToken().getAccessToken());
    }

}
