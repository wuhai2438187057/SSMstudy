package pojo.vo;

import pojo.Log;

public class MessageModel {
    private    Integer code;//状态码1：成功，0失败
    private    String  msg;//提示信息
    private   Object object= new Log(null,"name","password");//回显消息

    public MessageModel() {
    }

    public MessageModel(Integer code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
