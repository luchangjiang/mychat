package com.grady.mychat.config;

import com.grady.mychat.model.AccessToken;
import com.grady.mychat.model.JsapiSdk;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: mychat
 * @description: wechat config
 * @author: luchangjiang
 * @create: 2019-03-05 06:56
 **/
@Configuration
public class WeChatConfig {
    @Value("${wechat.token}")
    public static String token;

//    @Value("${wechat.appid:wx7e2bdb52e162650c}")
    public static final String appId="wx7e2bdb52e162650c";

//    @Value("${wechat.appsecret:ad8e1a566b94b330774be5ffbeb9ee19}")
    public static final String appSecret="ad8e1a566b94b330774be5ffbeb9ee19";

    //refresh by commandlinerunner and scheduler in WeChatRunner
    public static AccessToken accessToken;

    public static JsapiSdk jsapiTicket;

    public static final String baseUrl="http://grady.pagekite.me";

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static String getAccessTokenUrl(){
        String url =ACCESS_TOKEN_URL.replace("APPID", "wx7e2bdb52e162650c").replace("APPSECRET","ad8e1a566b94b330774be5ffbeb9ee19");
        return url;
    }
    //post
    public static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //post
    public static final String SET_INDUSTRY_URL="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    //get
    public static final String GET_INDUSTRY_URL="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    //post
    public static final String POST_TEMPLATE_ID_ADDR="https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
    //get
    public static final String GET_ALL_PRIVATE_TEMPLATE="https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
    //post
    public static final String DEL_PRIVATE_TEMPLATE="https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";
    //post
    public static final String SEND_TEMPLATE_MESSAGE="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    //POST
    public static final String QR_CODE_TICKET_URL="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
    //GET
    public static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    //GET
    public static final String QR_CODE_URL="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    //get
    public static final String USER_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public static final String AUTH_AUTHORIZE_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID";

    public static final String AUTH_CODE_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";

    public static final String AUTH_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    //get
    public static final String AUTH_USER_INFO="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

}
