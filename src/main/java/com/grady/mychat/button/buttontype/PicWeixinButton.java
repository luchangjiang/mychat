package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mychat
 * @description: pic weixin button
 * @author: luchangjiang
 * @create: 2019-03-05 11:02
 **/
@Data
public class PicWeixinButton extends BaseButton {
    private String type;
    private String key;
    private List<BaseButton> sub_button=new ArrayList<BaseButton>();

    public PicWeixinButton(String name, String key) {
        super(name);
        this.setType("pic_weixin");
        this.key = key;
    }
}
