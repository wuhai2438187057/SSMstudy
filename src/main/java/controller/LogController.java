package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.vo.MessageModel;
import service.EmployeeService;
import service.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogController {

    @Autowired
 public LogService logService;
    @Autowired
  public   EmployeeService employeeService;

    @RequestMapping("/login")
    public String login(Model model){
        MessageModel messageModel=new MessageModel();
        model.addAttribute("messageModel",messageModel);
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model){
        MessageModel messageModel=new MessageModel();
        model.addAttribute("messageModel",messageModel);
        return "register";
    }


    @RequestMapping("/registerRequest")
    public  String register(String name , String password, Model model ){
        MessageModel messageModel = logService.register(name, password);
        if(messageModel.getCode()==1){
            model.addAttribute("messageModel",messageModel);
            return "login";
        }else {
         model.addAttribute("messageModel",messageModel);
         return "register";
        }
    }

    @RequestMapping("/loginRequest")
    public String login(String name , String password, Model model, HttpServletRequest httpServletRequest){
        MessageModel messageModel = logService.login(name, password);
        if(messageModel.getCode()==1){
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("username",name);
            return "forward:/employeeManger/1";
        }else {
            model.addAttribute("messageModel",messageModel);
            return "login";
        }


    }



}
