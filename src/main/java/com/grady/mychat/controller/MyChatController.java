package com.grady.mychat.controller;

import com.alibaba.fastjson.JSONObject;
import com.grady.mychat.button.WeChatMenu;
import com.grady.mychat.common.msg.RestResponse;
import com.grady.mychat.config.MenuConfig;
import com.grady.mychat.config.WeChatConfig;
import com.grady.mychat.exception.BaseException;
import com.grady.mychat.model.WeiXinUser;
import com.grady.mychat.service.TemplateMessageService;
import com.grady.mychat.util.WeChatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

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
}
