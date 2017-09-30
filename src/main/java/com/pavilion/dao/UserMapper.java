package com.pavilion.dao;

import com.pavilion.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Delete("delete from user where id=#{id}")
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into user (username, password, mobile, email, nickname, avatar, role_id, create_time, last_update_time)\n" +
            "VALUES (#{username},#{password},#{mobile},#{email},#{nickname},#{avatar},#{roleId},datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'))")
    int insert(User record);

    @Select("select * from user where id=#{id}")
    User selectByPrimaryKey(Integer id);

    @Update("update user set username=#{username},password=#{password},mobile=#{mobile},email=#{email},nickname=#{nickname},avatar=#{avatar}" +
            "role_id=#{roleId},create_time=datetime(CURRENT_TIMESTAMP,'localtime'),last_update_time=datetime(CURRENT_TIMESTAMP,'localtime')")
    int updateByPrimaryKey(User record);

    @Select("select * from user where username=#{userName} LIMIT 0,1")
    User getUserByUserName(String userName);

    @Select("select * from user where username=#{userName} or mobile=#{userName} or email=#{userName} LIMIT 0,1")
    User getLoginUser(String userName);
}