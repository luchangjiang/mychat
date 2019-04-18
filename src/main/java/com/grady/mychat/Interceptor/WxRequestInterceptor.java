package com.grady.mychat.Interceptor;

/**
 * @program: mychat
 * @description: weixin request interceptor
 * @author: luchangjiang
 * @create: 2019-03-20 12:24
 **/
import com.alibaba.fastjson.JSONObject;
import com.grady.mychat.constant.WeChatConstants;
import com.grady.mychat.model.WechatSettings;
import com.grady.mychat.util.WeChatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * 微信请求拦截器
 *
 * @author smallk
 * @date 2018/12/3 0:39
 */
public class WxRequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String backURL = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            backURL += "?" + request.getQueryString();
        }
        HttpSession session = request.getSession();
        String userAgent = request.getHeader("User-Agent");
        //判断是否是微信浏览器
        if (userAgent == null || !userAgent.toLowerCase().contains("micromessenger")) {
            return true;
        }
        JSONObject wxUserInfoJson = (JSONObject) session.getAttribute("sessionWxUserInfoJson");
        if (wxUserInfoJson != null) {
            return true;
        }
        String code = request.getParameter("code");
        // 第一次访问
        if (StringUtils.isBlank(code)) {
            String uri= WeChatConstants.AUTH_CODE_URL.replace("APPID", WechatSettings.appId)
                    .replace("REDIRECT_URI", URLEncoder.encode(backURL, "UTF-8"))
                    .replace("SCOPE","snsapi_userinfo");
            response.sendRedirect(uri);
//            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
//                    + WeChatConfig.appId
//                    + "&redirect_uri="
//                    + URLEncoder.encode(backURL, "UTF-8")
//                    + "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
            return false;
        }
        // 微信认证回来
        JSONObject authJson = JSONObject.parseObject(WeChatUtil.getUserByCode(code));
        String openId = authJson.getString("openid");
        if (openId == null) {
            return false;
        }
        session.setAttribute("sessionWxUserInfoJson", authJson);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView e) throws Exception {
        //
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {
        //
    }
}
