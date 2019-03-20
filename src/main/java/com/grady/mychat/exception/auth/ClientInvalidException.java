package com.grady.mychat.exception.auth;

import com.grady.mychat.constant.CommonConstants;
import com.grady.mychat.exception.BaseException;

/**
 * Created by ace on 2017/9/10.
 */
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
