package com.pavilion.dao;

import com.pavilion.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Delete("delete from user where id=#{id}")
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into user (username, password, mobile, email, nickname, roleid, createtime, lastupdatetime, isdeleted)\n" +
            "VALUES (#{username},#{password},#{mobile},#{email},#{nickname},#{roleid},datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0)")
    int insert(User record);

    @Select("select * from user where id=#{id}")
    User selectByPrimaryKey(Integer id);

    @Update("update user set username=#{username},password=#{password},mobile=#{mobile},email=#{email},nickname=#{nickname}," +
            "roleid=#{roleid},createtime=datetime(CURRENT_TIMESTAMP,'localtime'),lastupdatetime=datetime(CURRENT_TIMESTAMP,'localtime'),isdeleted=#{isdeleted}")
    int updateByPrimaryKey(User record);
}