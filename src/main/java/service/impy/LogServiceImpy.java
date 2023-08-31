package service.impy;

import mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Log;
import pojo.vo.MessageModel;
import service.LogService;
@Service
public class LogServiceImpy implements LogService {
    @Autowired
    public LogMapper logMapper;
    @Override
    public MessageModel login(String name, String password) {
        MessageModel messageModel=new MessageModel();
        Log info = logMapper.getInfo(name);
        if (info==null){
            messageModel.setCode(0);
            messageModel.setMsg("用户不存在");
            messageModel.setObject(new Log(null,name,password));

        } else if (password==null||info.getPassword().equals(password)==false) {
            messageModel.setCode(0);
            messageModel.setMsg("密码错误，请重新输入");
            messageModel.setObject(new Log(null,name,password));

        }else{
            messageModel.setCode(1);
            messageModel.setMsg("success");
            messageModel.setObject(new Log(null,name,password));
        }
          return messageModel;
    }

    @Override
    public MessageModel register(String name, String password) {
        MessageModel messageModel=new MessageModel();
        Log info = logMapper.getInfo(name);
        if(info!=null){
            messageModel.setCode(0);
            messageModel.setMsg("用户名已存在,请重新注册");
            messageModel.setObject(new Log(null,name,password));

        }else if(password==null){
            messageModel.setCode(0);
            messageModel.setMsg("密码为空，请重新输入");
            messageModel.setObject(new Log(null,name,password));
        }else {
            messageModel.setCode(1);
            messageModel.setMsg("success");
            messageModel.setObject(new Log(null,name,password));
            logMapper.insertUser(new Log(null,name,password));

        }
        return messageModel;
        
    }
}
