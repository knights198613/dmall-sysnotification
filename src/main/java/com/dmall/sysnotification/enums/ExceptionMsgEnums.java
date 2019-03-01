package com.dmall.sysnotification.enums;

/**
 * Creator: jiang.wei
 * Date: 2019/3/1
 * DESC: 异常信息枚举类
 */

public enum ExceptionMsgEnums {
    PATH_ILLEGAL_EXC("0001", "路径非法"),
    PATH_NULL_EXC("0004", "路径为空"),
    PATH_NO_SUB_FOR_EPHEMERAL("0005", "临时节点创建路劲不包含层级，即临时节点不能创建子节点"),
    APPNAME_NULL_EXC("0002", "应用名称为空"),
    APPNAME_ILLEGAL_EXC("0003", "应用名称含有非法字符"),
    ;


    private String code;
    private String msg;

    ExceptionMsgEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
