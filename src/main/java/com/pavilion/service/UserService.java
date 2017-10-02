package com.pavilion.service;

import com.pavilion.dao.UserMapper;
import com.pavilion.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User getUserById(int id);

    User getUserByUserName(String userName);

    User getLoginUser(String userName);

    int insert(User user);

    int updateUser(User user);

    int updatePwd(Integer id, String newPwd);

    List<User> getPagedUsers(int page,int limit);

    int getUserCount();

    int deleteUser(int userId);

    int getSearchUserCount(String searchKey);

    List<User> getSearchPagedUsers(int page, int limit, String searchKey);

    User getExistUser(String username, String mobile, String email);
}
