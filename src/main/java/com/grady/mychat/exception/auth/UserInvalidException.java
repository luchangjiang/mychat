package com.grady.mychat.exception.auth;

import com.grady.mychat.constant.CommonConstants;
import com.grady.mychat.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
