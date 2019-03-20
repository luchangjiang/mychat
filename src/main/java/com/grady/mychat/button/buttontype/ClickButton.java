package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

/**
 * @program: mychat
 * @description: click button
 * @author: luchangjiang
 * @create: 2019-03-05 10:46
 **/
@Data
public class ClickButton extends BaseButton {
    private String type;
    private String key;

    public ClickButton(String name, String key) {
        super(name);
        this.setType("click");
        this.key = key;
    }
}
