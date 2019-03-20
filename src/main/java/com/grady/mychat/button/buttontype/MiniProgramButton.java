package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

/**
 * @program: mychat
 * @description: wechat auth button
 * @author: luchangjiang
 * @create: 2019-03-05 10:50
 **/
@Data
public class MiniProgramButton extends BaseButton {
    private String type;
    private String url;
    private String appId;
    private String pagepath;

    public MiniProgramButton(String name, String url, String appId, String pagepath) {
        super(name);
        this.setType("miniprogram");
        this.url = url;
        this.appId = appId;
        this.pagepath = pagepath;
    }
}
