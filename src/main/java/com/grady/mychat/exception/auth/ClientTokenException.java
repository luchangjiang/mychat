package com.grady.mychat.exception.auth;

import com.grady.mychat.constant.CommonConstants;
import com.grady.mychat.exception.BaseException;

/**
 *
 */
public class ClientTokenException extends BaseException {
    public ClientTokenException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
