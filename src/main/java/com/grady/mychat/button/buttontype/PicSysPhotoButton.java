package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mychat
 * @description: pic photo button
 * @author: luchangjiang
 * @create: 2019-03-05 10:59
 **/
@Data
public class PicSysPhotoButton extends BaseButton {
    private String type;
    private String key;
    private List<BaseButton> sub_button=new ArrayList<BaseButton>();

    public PicSysPhotoButton(String name, String key) {
        super(name);
        this.setType("pic_sysphoto");
        this.key = key;
    }
}
