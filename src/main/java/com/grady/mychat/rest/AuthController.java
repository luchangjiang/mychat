package com.grady.mychat.rest;

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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: mychat
 * @description: config wexin controller
 * @author: luchangjiang
 * @create: 2019-03-20 10:15
 **/
@RestController
@Api(produces="微信配置相关的接口")
@RequestMapping("auth")
public class AuthController {
    @Autowired
    TemplateMessageService templateMessageService;

    @PostMapping("/createMenu/{menuType}")
    @ApiOperation(value="创建微信菜单", notes="在微信公众号创建程序菜单")
    public RestResponse CreateMenu(@PathVariable int menuType){
        String uri = WeChatConfig.CREATE_MENU_URL.replace("ACCESS_TOKEN", WeChatUtil.getAccessToken().getAccessToken());

        WeChatMenu menu= MenuConfig.InitMenu(menuType);
        Object json = JSONObject.toJSON(menu);

        Mono<String> resp = WebClient.create()
                .post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(json))
                .retrieve()
                .bodyToMono(String.class);

        RestResponse response = new RestResponse().rel(true);
        response.setData(JSONObject.parseObject(resp.block()));
        return response;
    }

    @PostMapping("/setIndustry/{id1}/{id2}")
    @ApiOperation(value="微信设置公司行业", notes="微信设置公司行业")
    public RestResponse setIndustry(@PathVariable int id1, @PathVariable int id2) {
        return templateMessageService.setIndustry(id1, id2);
    }

    @GetMapping("/getIndustry")
    @ApiOperation(value="微信获取公司行业", notes="微信获取公司行业")
    public RestResponse getIndustry() {
        return templateMessageService.getIndustry();
    }

    @GetMapping("/getAllPrivateTemplates")
    @ApiOperation(value="获取所有模板", notes="获取所有的微信消息模板")
    public RestResponse getAllPrivateTemplates(){
        return templateMessageService.getAllPrivateTemplates();
    }

    @PostMapping("/getTemplateById/{templateId}")
    @ApiOperation(value="获取模板", notes="根据模板ID获取模板")
    public RestResponse getTemplateById(@PathVariable String templateId){
        return templateMessageService.getTemplateById(templateId);
    }
    @PostMapping("/delTemplateById/{templateId}")
    @ApiOperation(value="删除模板", notes="根据模板ID删除模板")
    public RestResponse delTemplateById(@PathVariable String templateId){
        return templateMessageService.delTemplateById(templateId);
    }

    @PostMapping("/sendTemplateMessage")
    @ApiOperation(value="发送消息", notes="根据模板ID发送模板消息")
    public RestResponse sendTemplateMessage(String message){
        return templateMessageService.sendTemplateMessage(message);
    }

    @PostMapping("/getQrCodeTicket")
    @ApiOperation(value="获取ticket", notes="获取微信二维码前的ticket")
    public RestResponse getQrCodeTicket(String message){
        return WeChatUtil.getQrCodeTicket(message);
    }

    @GetMapping("/getQrCode/{ticket}")
    @ApiOperation(value="获取二维码", notes="重定向到二维码")
    public void getQrCode(@PathVariable String ticket, HttpServletResponse response){
        try {
            response.sendRedirect(WeChatConfig.QR_CODE_URL.replace("TICKET", ticket));
        }catch (IOException e){
            throw new BaseException("重定向失败");
        }
    }

    @GetMapping("/getWeiXinUser/{openId}")
    @ApiOperation(value="获取用户信息", notes="获取用户信息")
    public RestResponse<WeiXinUser> getWeiXinUser(@PathVariable String openId){
        WeiXinUser user= WeChatUtil.getUserInfo(openId);
        RestResponse<WeiXinUser> resp= new RestResponse<WeiXinUser>().rel(true);
        resp.setData(user);
        return resp;
    }

    @GetMapping("/uncoCallback")
    @ApiOperation(value="授权登录用户信息", notes="获取授权登录用户信息")
    public void UncoCallback(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String code = request.getParameter("code");
        String user = WeChatUtil.getUserByCode(code);
        //将数据存储到session中
        HttpSession session = request.getSession();
        session.setAttribute("uncoUserInfo", user);

        response.sendRedirect("/mychat/hello");
    }
}
