package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

/**
 * @program: mychat
 * @description: view limited button
 * @author: luchangjiang
 * @create: 2019-03-05 11:06
 **/
@Data
public class ViewLimitedButton extends BaseButton {
    private String type;
    private String media_id;

    public ViewLimitedButton(String name, String media_id) {
        super(name);
        this.setType("view_limited");
        this.media_id = media_id;
    }
}
