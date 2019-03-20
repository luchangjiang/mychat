package com.grady.mychat.common.msg;

/**
 * Created by Ace on 2017/6/11.
 */
public class RestResponse<T> extends BaseResponse {

    T data;
    boolean rel;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }


    public RestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }

    public RestResponse data(T data) {
        this.setData(data);
        return this;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
