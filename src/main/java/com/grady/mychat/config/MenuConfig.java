package com.grady.mychat.config;

import com.grady.mychat.button.*;
import com.grady.mychat.button.buttontype.*;

/**
 * @program: mychat
 * @description: menu config
 * @author: luchangjiang
 * @create: 2019-03-05 11:43
 **/
public class MenuConfig {
    public static WeChatMenu InitMenu(int menuType){
        WeChatMenu menu=new WeChatMenu();
        if(1==menuType){
            menu = MenuConfig.InitMenu1();
        }
        else{
            menu = MenuConfig.InitMenu2();
        }
        return menu;
    }

    private static WeChatMenu InitMenu1(){
        WeChatMenu menu=new WeChatMenu();

        ClickButton clickButton=new ClickButton("今日歌曲","V1001_TODAY_MUSIC");
        menu.getButton().add(clickButton);

        BaseButton baseButton=new BaseButton("菜单");
        baseButton.getSub_button().add(new ViewButton("Hello",WeChatConfig.baseUrl + "/mychat/hello"));
        baseButton.getSub_button().add(new ViewButton("Jssdk",WeChatConfig.baseUrl + "/mychat/jssdk"));
//        MiniProgramButton miniProgramButton=new MiniProgramButton("wxa","http://mp.weixin.qq.com",WeChatConfig.appId,"pages/lunar/index");
//        baseButton.getSub_button().add(miniProgramButton);
        ClickButton clickButton2=new ClickButton("赞一下我们","V1001_GOOD");
        baseButton.getSub_button().add(clickButton2);

        menu.getButton().add(baseButton);
        return menu;
    }

    private static WeChatMenu InitMenu2(){
        WeChatMenu menu=new WeChatMenu();

        BaseButton baseButton=new BaseButton("扫码");

        ScanCodeWaitMsgButton scanCodeWaitMsgButton=new ScanCodeWaitMsgButton("扫码带提示","rselfmenu_0_0");
        baseButton.getSub_button().add(scanCodeWaitMsgButton);
        ScanCodePushButton scanCodePushButton=new ScanCodePushButton("扫码推事件","rselfmenu_0_1");
        baseButton.getSub_button().add(scanCodePushButton);
        menu.getButton().add(baseButton);

        BaseButton baseButton2=new BaseButton("发图");
        PicSysPhotoButton picSysPhotoButton=new PicSysPhotoButton("系统拍照发图","rselfmenu_1_0");
        baseButton2.getSub_button().add(picSysPhotoButton);
        PicPhotoAlbumButton picPhotoAlbumButton=new PicPhotoAlbumButton("拍照或者相册发图","rselfmenu_1_1");
        baseButton2.getSub_button().add(picPhotoAlbumButton);
        PicWeixinButton picWeixinButton=new PicWeixinButton("微信相册发图","rselfmenu_1_2");
        baseButton2.getSub_button().add(picWeixinButton);
        menu.getButton().add(baseButton2);

        LocationSelectButton locationSelectButton=new LocationSelectButton("发送位置","rselfmenu_2_0");
        menu.getButton().add(locationSelectButton);
        /*MediaIdButton mediaIdButton=new MediaIdButton("图片","MEDIA_ID1");
        menu.getButton().add(mediaIdButton);
        ViewLimitedButton viewLimitedButton=new ViewLimitedButton("图文消息","MEDIA_ID2");
        menu.getButton().add(viewLimitedButton);*/

        return menu;
    }
}
