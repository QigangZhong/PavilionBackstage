package com.pavilion.service;

import com.pavilion.dao.UserMapper;
import com.pavilion.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {

    User getUserById(int id);

    User getUserByUserName(String userName);
}
