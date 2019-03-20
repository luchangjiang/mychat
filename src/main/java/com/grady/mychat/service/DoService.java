package com.grady.mychat.service;

import com.grady.mychat.util.MessageUtil;
import com.grady.mychat.util.WeChatUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @program: mychat
 * @description: message service
 * @author: luchangjiang
 * @create: 2019-03-05 05:53
 **/
@RestController
public class DoService {
    @GetMapping("/")
    public String Test(){
        return "it is ok!";
    }

    @GetMapping("/wechat")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signagure = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //校验签名
        if(WeChatUtil.checkSignagure(timestamp,nonce,signagure)){
            PrintWriter out =response.getWriter();
            //原样返回echostr
            out.print(echostr);
            out.flush();
            out.close();
        }
        else
        {
            System.out.println("接入失败！");
        }
    }
    @PostMapping("/wechat")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        ServletInputStream stream = request.getInputStream();
        Map<String, String> map = MessageUtil.parseXml(stream);
        System.out.println(map);
        String resMsg =MessageUtil.getResponse(map);
        System.out.println(resMsg);
        PrintWriter out=response.getWriter();
        out.print(resMsg);
        out.flush();
        out.close();

    }

}
