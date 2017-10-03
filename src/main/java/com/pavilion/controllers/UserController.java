package com.pavilion.controllers;

import com.pavilion.model.ErrorCode;
import com.pavilion.model.Result;
import com.pavilion.model.Role;
import com.pavilion.model.User;
import com.pavilion.model.dto.RoleDto;
import com.pavilion.model.dto.TableData;
import com.pavilion.model.dto.UserDto;
import com.pavilion.service.MailService;
import com.pavilion.service.RoleService;
import com.pavilion.service.UserRoleService;
import com.pavilion.service.UserService;
import com.pavilion.util.MD5Util;
import com.pavilion.util.PasswordUtil;
import com.pavilion.util.ValidateUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    MailService mailService;
    @Autowired
    UserRoleService userRoleService;

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
    public String add(Model model){
        List<Role> roles = roleService.getAll();

        model.addAttribute("roles",roles);
        return "user/add";
    }

    @RequestMapping(value="/user/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> add(User user,String roles){
        if(user==null){
            return Result.parameterError();
        }

        if(StringUtils.isEmptyOrWhitespace(user.getUsername()) || StringUtils.isEmptyOrWhitespace(user.getMobile())|| StringUtils.isEmptyOrWhitespace(user.getEmail())){
            return Result.fail(ErrorCode.Error.getCode(),"不能为空","");
        }

        if(user.getUsername().length()<8 || user.getUsername().length()>16){
            return Result.fail("用户名长度建议8-16之间");
        }

        if(!ValidateUtil.isMobileNumber(user.getMobile()) || !ValidateUtil.isEmailAddress(user.getEmail())){
            return Result.fail("手机号或邮箱地址不正确");
        }

        String pwd=PasswordUtil.getRandomPassword(8);
        user.setPassword(pwd);
        user.setAvatar("http://www.gravatar.com/avatar/");

        User existUser=userService.getExistUser(user.getUsername(),user.getMobile(),user.getEmail());
        if(existUser!=null){
            return Result.fail("添加失败,用户名、手机号或者邮箱已存在");
        }

        //创建用户
        int result=userService.insert(user);
        if(result<=0){
            return Result.fail(ErrorCode.Error.getCode(),"添加用户失败","");
        }

        //关联角色
        User thisUser=userService.getUserByUserName(user.getUsername());
        userRoleService.deleteByUserId(thisUser.getId());
        if (roles.length()>0){
            String[] strRoles=roles.split(",");
            for (int i = 0; i <strRoles.length ; i++) {
                int roleId=Integer.parseInt(strRoles[i]);
                int row = userRoleService.insert(thisUser.getId(),roleId);
            }
        }

        try {
            //给新用户发密码邮件
            mailService.Send(user.getEmail(), "请查收PavilionBackstage密码", "您的初始密码为" + pwd);
        }catch (Exception ex)
        {
            logger.error(ex.getStackTrace().toString());
            return Result.fail(-1,"用户注册成功,但密码通知邮件发送失败");
        }

        return Result.success("添加成功,初始密码已发送至用户邮箱","");
    }

    @RequestMapping(value="/user/profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest request,Map map){
        User user=(User) request.getSession().getAttribute("user");
        map.put("username",user.getUsername());
        map.put("mobile",user.getMobile());
        map.put("email",user.getEmail());
        map.put("nickname",user.getNickname());

        List<Role> roles=roleService.getRolesByUserId(user.getId());
        StringBuilder roleNames =new StringBuilder();
        for (Role role: roles ) {
            roleNames.append(role.getName()+",");
        }

        if(roles.size()>0) {
            map.put("roleName", roleNames.toString().substring(0, roleNames.toString().length() - 1));
        }

        return "user/myProfile";
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


    @RequestMapping(value="/user/editPwd", method = RequestMethod.GET)
    public String editPwd(){
        return "user/editPwd";
    }

    @RequestMapping(value="/user/editPwd", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> editPwd(HttpServletRequest request,String oldPwd, String newPwd, String confirmNewPwd){
        if (StringUtils.isEmptyOrWhitespace(oldPwd) || StringUtils.isEmptyOrWhitespace(newPwd) || StringUtils.isEmptyOrWhitespace(confirmNewPwd)){
            return Result.parameterError();
        }

        if(!newPwd.equals(confirmNewPwd)){
            return Result.fail(-1,"两次输入密码不一致","");
        }

        User user=(User) request.getSession().getAttribute("user");
        if(!MD5Util.encode(oldPwd).equals(user.getPassword())){
            return Result.fail(-1,"原密码输入不正确","");
        }

        int row = userService.updatePwd(user.getId(),newPwd);

        return Result.success("修改成功","");
    }

    @RequestMapping(value="/user/findPwd", method = RequestMethod.GET)
    public String findPwd(HttpServletRequest request,Model model){
        User user=(User) request.getSession().getAttribute("user");
        model.addAttribute("email",user.getEmail());
        return "user/findPwd";
    }

    @RequestMapping(value="/user/findPwd", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> findPwd(HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            return Result.fail(-1,"用户未登录","");
        }

        if(StringUtils.isEmptyOrWhitespace(user.getEmail())){
            return Result.fail(-1,"尚未设置邮箱地址,无法重置密码","");
        }

        String newPwd= PasswordUtil.getRandomPassword(8);
        userService.updatePwd(user.getId(),newPwd);
        mailService.Send(user.getEmail(),"Pavilion Backstage新密码","新密码为 "+newPwd);

        return Result.success("新密码已发送至您的邮箱","");
    }

    @RequestMapping(value="/user/query", method = RequestMethod.GET)
    public String query(){
        return "user/query";
    }

    @RequestMapping(value="/user/queryUsers", method = RequestMethod.GET)
    @ResponseBody
    public TableData<List<UserDto>> queryUsers(int page,int limit,String searchKey){
        TableData<List<UserDto>> data=new TableData<List<UserDto>>();

        int count=0;
        List<UserDto> userDtos= new ArrayList<>();

        if(StringUtils.isEmptyOrWhitespace(searchKey)) {
            count = userService.getUserCount();
            List<User> users = userService.getPagedUsers(page, limit);
            userDtos = new ModelMapper().map(users, new TypeToken<List<UserDto>>() {
            }.getType());
        }else{
            count = userService.getSearchUserCount(searchKey);
            List<User> users = userService.getSearchPagedUsers(page, limit,searchKey);
            userDtos = new ModelMapper().map(users, new TypeToken<List<UserDto>>() {
            }.getType());
        }

        data.setCount(count);
        data.setData(userDtos);
        return data;
    }

    @RequestMapping(value="/user/update", method = RequestMethod.GET)
    public String update(int userId,Map map){
        User user=userService.getUserById(userId);
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        map.put("mobile",user.getMobile());
        map.put("email",user.getEmail());
        map.put("nickname",user.getNickname());

        List<Role> currentRoles=roleService.getRolesByUserId(userId);
        List<Integer> currentRoleIds=currentRoles.stream().map(Role::getId).collect(Collectors.toList());
        List<Role> allRoles=roleService.getAll();
        List<RoleDto> list=new ArrayList<>();
        for (int i = 0; i < allRoles.size(); i++) {
            Role role =  allRoles.get(i);
            boolean isActive=currentRoleIds.contains(role.getId());
            RoleDto dto=new RoleDto(role.getId(),role.getName(),isActive);
            list.add(dto);
        }
        map.put("allRoles",list);

        return "user/update";
    }

    @RequestMapping(value="/user/update", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> update(User user, String roles){
        User userInDb=userService.getUserById(user.getId());
        userInDb.setUsername(user.getUsername());
        userInDb.setMobile(user.getMobile());
        userInDb.setEmail(user.getEmail());
        userInDb.setNickname(user.getNickname());
        userInDb.setLastUpdateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        int row = userService.updateUser(userInDb);
        if(row<=0){
            return Result.fail("修改失败");
        }

        //关联角色
        User thisUser=userService.getUserById(user.getId());
        userRoleService.deleteByUserId(thisUser.getId());
        if (roles.length()>0){
            String[] strRoles=roles.split(",");
            for (int i = 0; i <strRoles.length ; i++) {
                int roleId=Integer.parseInt(strRoles[i]);
                userRoleService.insert(thisUser.getId(),roleId);
            }
        }

        return Result.success("修改成功","");
    }


    @RequestMapping(value="/user/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> deleteUser(int userId){
        int row = userService.deleteUser(userId);
        if(row<=0){
            return Result.fail("删除失败");
        }
        return Result.success("删除成功","");
    }



    @RequestMapping(value="/user/editRole", method = RequestMethod.GET)
    public String editRole(int userId){
        return "user/editRole";
    }

    @RequestMapping(value="/user/editRole", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> editRole(int userId,int[] roleIds){


        return Result.success("修改成功","");
    }


}
