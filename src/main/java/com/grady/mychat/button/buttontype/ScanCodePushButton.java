package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mychat
 * @description: scan code and push
 * @author: luchangjiang
 * @create: 2019-03-05 10:58
 **/
@Data
public class ScanCodePushButton extends BaseButton {
    private String type;
    private String key;
    private List<BaseButton> sub_button=new ArrayList<BaseButton>();

    public ScanCodePushButton(String name, String key) {
        super(name);
        this.setType("scancode_push");
        this.key = key;
    }
}
