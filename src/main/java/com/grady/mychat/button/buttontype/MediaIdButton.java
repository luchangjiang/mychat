package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

/**
 * @program: mychat
 * @description: media id button
 * @author: luchangjiang
 * @create: 2019-03-05 11:04
 **/
@Data
public class MediaIdButton extends BaseButton {
    private String type;
    private String media_id;

    public MediaIdButton(String name, String media_id) {
        super(name);
        this.setType("media_id");
        this.media_id = media_id;
    }
}
