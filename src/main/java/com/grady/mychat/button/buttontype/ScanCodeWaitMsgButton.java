package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mychat
 * @description: scancode_waitmsg
 * @author: luchangjiang
 * @create: 2019-03-05 10:54
 **/
@Data
public class ScanCodeWaitMsgButton extends BaseButton {
    private String type;
    private String key;
    private List<BaseButton> sub_button=new ArrayList<BaseButton>();

    public ScanCodeWaitMsgButton(String name, String key) {
        super(name);
        this.setType("scancode_waitmsg");
        this.key = key;
    }
}
