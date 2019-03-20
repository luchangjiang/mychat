package com.grady.mychat.button.buttontype;

import com.grady.mychat.button.BaseButton;
import lombok.Data;

/**
 * @program: mychat
 * @description: location select button
 * @author: luchangjiang
 * @create: 2019-03-05 11:03
 **/
@Data
public class LocationSelectButton extends BaseButton {
    private String type;
    private String key;

    public LocationSelectButton(String name, String key) {
        super(name);
        this.setType("location_select");
        this.key = key;
    }
}
