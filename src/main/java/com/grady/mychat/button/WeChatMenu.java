package com.grady.mychat.button;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mychat
 * @description: menus
 * @author: luchangjiang
 * @create: 2019-03-05 11:38
 **/
@Data
public class WeChatMenu {
    private List<BaseButton> button= new ArrayList<BaseButton>();

    public WeChatMenu() {
    }
}
