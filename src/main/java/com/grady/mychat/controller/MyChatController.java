package com.grady.mychat.controller;

import com.alibaba.fastjson.JSONObject;
import com.grady.mychat.constant.WeChatConstants;
import com.grady.mychat.model.JsapiSdk;
import com.grady.mychat.model.WechatSettings;
import com.grady.mychat.model.WeiXinUser;
import com.grady.mychat.service.TemplateMessageService;
import com.grady.mychat.util.WeChatUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: mychat
 * @description: mychat controller
 * @author: luchangjiang
 * @create: 2019-03-05 06:01
 **/
@Controller
@Api(produces="微信应用相关的接口")
@RequestMapping("mychat")
public class MyChatController {
    @Autowired
    TemplateMessageService templateMessageService;

    @GetMapping(value = "/hello")
    public String hello(Model model, HttpServletRequest request) {
        HttpSession session=request.getSession();

        if(session.getAttribute("sessionWxUserInfoJson")!=null) {
            WeiXinUser weiXinUser = JSONObject.parseObject(session.getAttribute("sessionWxUserInfoJson").toString(), WeiXinUser.class);

            model.addAttribute("openid", weiXinUser.getOpenId());
            model.addAttribute("nickname", weiXinUser.getNickname());
            model.addAttribute("headimgurl", weiXinUser.getHeadImgUrl());
        }
        else{
            model.addAttribute("openid", "");
            model.addAttribute("nickname", "未获取到用户信息！");
        }
        return "hello";
    }

    @GetMapping(value = "/jssdk")
    public String Jssdk(Model model, HttpServletRequest request) {
        HttpSession session=request.getSession();

        JsapiSdk jsapiTicket = WeChatUtil.getJsapiTicket();

        model.addAttribute("appid", WechatSettings.appId);
        model.addAttribute("timestamp", jsapiTicket.getTimestamp());
        model.addAttribute("noncestr", jsapiTicket.getNoncestr());
        model.addAttribute("url", jsapiTicket.getUrl());
        model.addAttribute("signature", jsapiTicket.getSignature());
        return "jssdk";
    }
}
