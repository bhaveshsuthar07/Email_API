package com_email_api.helper;

public class Flag {

    private String msg;
    private  String set;

    public String getMsg() {
        return msg;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Flag(String msg, String set) {
        this.msg = msg;
        this.set = set;
    }
}
