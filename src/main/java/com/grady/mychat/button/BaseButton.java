package com.grady.mychat.button;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mychat
 * @description: abstract button
 * @author: luchangjiang
 * @create: 2019-03-05 10:42
 **/
@Data
public class BaseButton {
    private String name;

    private List<BaseButton> sub_button=new ArrayList<BaseButton>();

    public BaseButton(String name) {
        this.name = name;
    }
}
