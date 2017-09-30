package com.pavilion.controllers;

import com.pavilion.domain.Result;
import com.pavilion.domain.User;
import com.pavilion.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/user/getUserNameById")
    public String getUserNameById(){
        return userService.getUserById(2).getUsername();
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> login(HttpServletRequest request){
        Result<String> result=new Result<String>();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        logger.info("user is trying to login, username:"+userName+" password:"+password);

        User user=userService.getUserByUserName(userName);
        if(user==null){
            return Result.fail(-1,"用户不存在","");
        }

        if(!password.equals(user.getPassword())){
            return Result.fail(-1,"密码不正确","");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user",user);

        return Result.success("登陆成功","");
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
        return "ok";
    }
}
