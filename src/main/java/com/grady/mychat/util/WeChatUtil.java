package com.grady.mychat.util;

import com.alibaba.fastjson.JSONObject;
import com.grady.mychat.common.msg.RestResponse;
import com.grady.mychat.config.WeChatConfig;
import com.grady.mychat.model.AccessToken;
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
                .get().uri(WeChatConfig.getAccessTokenUrl())
                .retrieve()
                .bodyToMono(String.class);
        JSONObject jsonObject = JSONObject.parseObject(resp.block());

        System.out.println(jsonObject.toJSONString());
        String token=jsonObject.getString("access_token");
        String expireIn =jsonObject.getString("expires_in");
        AccessToken accessToken=new AccessToken(token, expireIn);
        System.out.println(accessToken);
        WeChatConfig.accessToken = accessToken;
    }

    public static AccessToken getAccessToken(){
        if(WeChatConfig.accessToken ==null||WeChatConfig.accessToken.isExpired()){
            refreshAccessToken();
        }
        return WeChatConfig.accessToken;
    }

    public static RestResponse getQrCodeTicket(String message){
        String uri = WeChatUtil.replaceAccessToken(WeChatConfig.QR_CODE_TICKET_URL);

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

    public static String getUserByCode(String code){
        String tokenUri=WeChatConfig.AUTH_ACCESS_TOKEN_URL.replace("APPID",WeChatConfig.appId)
                .replace("SECRET",WeChatConfig.appSecret)
                .replace("CODE",code);
        String accessTokenObj = WebClient.create()
                .get().uri(tokenUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject jsonObject=JSONObject.parseObject(accessTokenObj);
        String authToken=jsonObject.getString("access_token");
        String openid=jsonObject.getString("openid");

        String userUri=WeChatConfig.AUTH_USER_INFO.replace("ACCESS_TOKEN",authToken)
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
        String uri = WeChatUtil.replaceAccessToken(WeChatConfig.USER_INFO_URL).replace("OPENID", openId);

        WeiXinUser resp= WebClient.create()
                .get().uri(uri)
                .retrieve()
                .bodyToMono(WeiXinUser.class)
                .block();

        return resp;
    }

    public static Boolean checkSignagure(String timestamp, String nonce, String signature){
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] arr=new String[]{WeChatConfig.token, timestamp,nonce};
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

    public static String replaceAccessToken(String uri){
        return uri.replace("ACCESS_TOKEN", WeChatUtil.getAccessToken().getAccessToken());
    }

}