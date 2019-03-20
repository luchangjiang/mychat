package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mychat
 * @description: photo or album button
 * @author: luchangjiang
 * @create: 2019-03-05 11:01
 **/
@Data
public class PicPhotoAlbumButton extends BaseButton {
    private String type;
    private String key;
    private List<BaseButton> sub_button=new ArrayList<BaseButton>();

    public PicPhotoAlbumButton(String name, String key) {
        super(name);
        this.setType("pic_photo_or_album");
        this.key = key;
    }
}
