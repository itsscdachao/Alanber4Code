package com.itssc.tool_base.base_mvp.bean;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private int errcode;
    private String errmsg;
    private T result;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
