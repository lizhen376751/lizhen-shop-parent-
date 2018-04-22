package com.lizhen.base;

import lombok.ToString;

/**
 * 返回参数进行封装
 * Created by lizhen on 2018/4/20 0020.
 */
@ToString
public class ResponseBase {
    //返回状态码
    private Integer rtncode;
    //状态信息
    private String rtnmsg;
    //响应数据
    private Object rtndata;
    public ResponseBase() {

    }

    public ResponseBase(Integer rtncode, String rtnmsg, Object rtndata) {
        super();
        this.rtncode = rtncode;
        this.rtnmsg = rtnmsg;
        this.rtndata = rtndata;
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "rtncode=" + rtncode +
                ", rtnmsg='" + rtnmsg + '\'' +
                ", rtndata=" + rtndata +
                '}';
    }

    public Integer getRtncode() {
        return rtncode;
    }

    public void setRtncode(Integer rtncode) {
        this.rtncode = rtncode;
    }

    public String getRtnmsg() {
        return rtnmsg;
    }

    public void setRtnmsg(String rtnmsg) {
        this.rtnmsg = rtnmsg;
    }

    public Object getRtndata() {
        return rtndata;
    }

    public void setRtndata(Object rtndata) {
        this.rtndata = rtndata;
    }
}
