package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

/**
 * @program: mychat
 * @description: view button
 * @author: luchangjiang
 * @create: 2019-03-05 10:48
 **/
@Data
public class ViewButton extends BaseButton {
    private String type;
    private String url;

    public ViewButton(String name, String url) {
        super(name);
        this.setType("view");
        this.url = url;
    }
}
