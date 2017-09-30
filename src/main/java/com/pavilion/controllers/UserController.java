package com.pavilion.controllers;

import com.pavilion.domain.ErrorCode;
import com.pavilion.domain.Result;
import com.pavilion.domain.User;
import com.pavilion.service.RoleService;
import com.pavilion.service.UserService;
import com.pavilion.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.time.LocalDateTime;

@Controller
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

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

        if(StringUtils.isEmptyOrWhitespace(userName) || StringUtils.isEmptyOrWhitespace(password)){
            return Result.parameterError();
        }

        User user=userService.getLoginUser(userName);
        if(user==null){
            return Result.fail(ErrorCode.Error.getCode(),"用户不存在","");
        }

        if(!MD5Util.encode(password).equals(user.getPassword())){
            return Result.fail(ErrorCode.Error.getCode(),"密码不正确","");
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

    @RequestMapping(value="/user/add", method = RequestMethod.GET)
    public String add(){
        return "add";
    }

    @RequestMapping(value="/user/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> add(User user){
        if(user==null){
            return Result.parameterError();
        }

        if(StringUtils.isEmptyOrWhitespace(user.getUsername()) || StringUtils.isEmptyOrWhitespace(user.getPassword())){
            return Result.fail(ErrorCode.Error.getCode(),"用户名或者密码不能为空","");
        }

        int result=userService.insert(user);
        if(result!=1){
            return Result.fail(ErrorCode.Error.getCode(),"添加用户失败","");
        }

        return Result.success("添加成功","");
    }

    @RequestMapping(value="/user/update", method = RequestMethod.GET)
    public String update(){
        return "update";
    }

    @RequestMapping(value="/user/update", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> update(User user){
        if(user==null){
            return Result.parameterError();
        }

        if(StringUtils.isEmptyOrWhitespace(user.getUsername()) || StringUtils.isEmptyOrWhitespace(user.getPassword())){
            return Result.fail(ErrorCode.Error.getCode(),"用户名或者密码不能为空","");
        }

        int result=userService.insert(user);
        if(result!=1){
            return Result.fail(ErrorCode.Error.getCode(),"修改用户失败","");
        }

        return Result.success("修改成功","");
    }

    @RequestMapping(value="/user/profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest request,Map map){
        User user=(User) request.getSession().getAttribute("user");
        map.put("username",user.getUsername());
        map.put("mobile",user.getMobile());
        map.put("email",user.getEmail());
        map.put("nickname",user.getNickname());

        String roleName=roleService.getRoleNameById(user.getRoleId());
        map.put("roleName",roleName);

        return "user/profile";
    }

    @RequestMapping(value="/user/updateProfile", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateProfile(HttpServletRequest request,String userName,String password,String mobile,String email,String nickname){
        User user=(User) request.getSession().getAttribute("user");
        if(user==null || user.getId()<=0){
            return Result.fail(-1,"修改失败","");
        }

        User userInDb=userService.getUserById(user.getId());
        if(!MD5Util.encode(password).equals(userInDb.getPassword())){
            return Result.fail(-1,"修改失败, 密码不正确","");
        }

        userInDb.setUsername(userName);
        userInDb.setMobile(mobile);
        userInDb.setEmail(email);
        userInDb.setNickname(nickname);
        userInDb.setLastUpdateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        int row = userService.updateUser(userInDb);
        if(row>0){
            request.getSession().setAttribute("user",userInDb);
        }

        return Result.success("修改成功","");
    }
}
