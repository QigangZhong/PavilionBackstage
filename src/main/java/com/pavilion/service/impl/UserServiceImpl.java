package com.pavilion.service.impl;

import com.pavilion.dao.UserMapper;
import com.pavilion.domain.User;
import com.pavilion.service.UserService;
import com.pavilion.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserById(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public User getLoginUser(String userName) {
        return userMapper.getLoginUser(userName);
    }

    @Override
    public int insert(User user) {
        String pwd=user.getPassword();
        if(pwd.length()!=32){
            user.setPassword(MD5Util.encode(pwd));
        }

        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        String pwd=user.getPassword();
        if(pwd.length()!=32){
            user.setPassword(MD5Util.encode(pwd));
        }

        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int updatePwd(Integer id, String newPwd) {
        if(newPwd.length()!=32){
            newPwd=MD5Util.encode(newPwd);
        }
        return userMapper.updatePwd(id,newPwd);
    }

    @Override
    public List<User> getPagedUsers(int page, int limit) {
        int offset=limit*(page-1);
        return userMapper.getPagedUsers(offset,limit);
    }

    @Override
    public int getUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    public int deleteUser(int userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int getSearchUserCount(String searchKey) {
        return userMapper.getSearchUserCount(searchKey);
    }

    @Override
    public List<User> getSearchPagedUsers(int page, int limit, String searchKey) {
        int offset=limit*(page-1);
        return userMapper.getSearchPagedUsers(offset, limit, searchKey);
    }

    @Override
    public User getExistUser(String username, String mobile, String email) {
        return userMapper.getExistUser(username,mobile,email);
    }
}
