package com.grady.mychat.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.grady.mychat.common.msg.RestResponse;
import com.grady.mychat.config.WeChatConfig;
import com.grady.mychat.model.WeiXinUser;
import com.grady.mychat.service.TemplateMessageService;
import com.grady.mychat.util.WeChatUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: mychat
 * @description: implement of template message
 * @author: luchangjiang
 * @create: 2019-03-05 16:24
 **/
@Service
public class TemplateMessageServiceImpl implements TemplateMessageService {
    public RestResponse setIndustry(int id1, int id2){
        Map<String, Integer> map =new HashMap<>();
        map.put("industry_id1", id1);
        map.put("industry_id2", id2);

        Object json = JSONObject.toJSON(map);

        String uri = WeChatUtil.replaceAccessToken(WeChatConfig.SET_INDUSTRY_URL);
        Mono<String> resp= WebClient.create()
                .post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(json))
                .retrieve()
                .bodyToMono(String.class);

        RestResponse response = new RestResponse().rel(true);
        response.setData(JSONObject.parseObject(resp.block()));
        return response;
    }

    public RestResponse getIndustry(){
        String uri = WeChatUtil.replaceAccessToken(WeChatConfig.GET_INDUSTRY_URL);

        Mono<String> resp= WebClient.create()
                .get().uri(uri)
                .retrieve()
                .bodyToMono(String.class);

        RestResponse response = new RestResponse().rel(true);
        response.setData(JSONObject.parseObject(resp.block()));
        return response;
    }

    public RestResponse getAllPrivateTemplates(){
        String uri = WeChatUtil.replaceAccessToken(WeChatConfig.GET_ALL_PRIVATE_TEMPLATE);

        Mono<String> resp= WebClient.create()
                .get().uri(uri)
                .retrieve()
                .bodyToMono(String.class);

        RestResponse response = new RestResponse().rel(true);
        response.setData(JSONObject.parseObject(resp.block()));
        return response;
    }

    public RestResponse getTemplateById(String templateId){
        String uri = WeChatUtil.replaceAccessToken(WeChatConfig.POST_TEMPLATE_ID_ADDR);

        String postData=String.format("{template_id_short:{0}}", templateId);

        Mono<String> resp= WebClient.create()
                .post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(postData))
                .retrieve()
                .bodyToMono(String.class);

        RestResponse response = new RestResponse().rel(true);
        response.setData(JSONObject.parseObject(resp.block()));
        return response;
    }

    public RestResponse delTemplateById(String templateId){
        String uri = WeChatUtil.replaceAccessToken(WeChatConfig.DEL_PRIVATE_TEMPLATE);

        String postData=String.format("{template_id:{0}}", templateId);

        Mono<String> resp= WebClient.create()
                .post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(postData))
                .retrieve()
                .bodyToMono(String.class);

        RestResponse response = new RestResponse().rel(true);
        response.setData(JSONObject.parseObject(resp.block()));
        return response;
    }

    public RestResponse sendTemplateMessage(String message){
        String uri = WeChatUtil.replaceAccessToken(WeChatConfig.SEND_TEMPLATE_MESSAGE);

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


}
