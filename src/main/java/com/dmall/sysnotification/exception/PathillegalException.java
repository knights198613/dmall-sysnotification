package com.dmall.sysnotification.exception;

import com.dmall.sysnotification.enums.ExceptionMsgEnums;

/**
 * Creator: jiang.wei
 * Date: 2019/3/1
 * DESC: 路径异常类
 */

public class PathillegalException extends RuntimeException {

    private String code;
    private String msg;

    public PathillegalException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public PathillegalException(ExceptionMsgEnums exceptionMsgEnums) {
        super(exceptionMsgEnums.getMsg());
        setCode(exceptionMsgEnums.getCode());
        setMsg(exceptionMsgEnums.getMsg());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
