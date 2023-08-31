package service;

import pojo.vo.MessageModel;

public interface LogService {
    //log的登录
    public MessageModel login(String name, String password);



    //用户注册
    public  MessageModel register(String name,String password);

}
