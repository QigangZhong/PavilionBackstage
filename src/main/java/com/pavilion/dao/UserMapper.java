package com.pavilion.dao;

import com.pavilion.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Delete("delete from user where id=#{id}")
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into user (username, password, mobile, email, nickname, avatar, create_time, last_update_time)\n" +
            "VALUES (#{username},#{password},#{mobile},#{email},#{nickname},#{avatar},datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'))")
    int insert(User record);

    @Select("select * from user where id=#{id}")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "lastUpdateTime", column = "last_update_time")
    })
    User selectByPrimaryKey(Integer id);

    @Update("update user set username=#{username},password=#{password},mobile=#{mobile},email=#{email},nickname=#{nickname},avatar=#{avatar}," +
            "last_update_time=datetime(CURRENT_TIMESTAMP,'localtime') where id=#{id}")
    int updateByPrimaryKey(User record);

    @Select("select * from user where username=#{userName} LIMIT 0,1")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "lastUpdateTime", column = "last_update_time")
    })
    User getUserByUserName(String userName);

    @Select("select * from user where username=#{userName} or mobile=#{userName} or email=#{userName} LIMIT 0,1")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "lastUpdateTime", column = "last_update_time")
    })
    User getLoginUser(String userName);

    @Update("update user set password=#{password},last_update_time=datetime(CURRENT_TIMESTAMP,'localtime') where id=#{id}")
    int updatePwd(@Param("id") Integer id, @Param("password") String newPwd);

    @Select("select * from user limit #{offset},#{limit}")
    List<User> getPagedUsers(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(1) from user")
    int getUserCount();

//    @Select("select count(1) as count from user where " +
//            " username like concat(concat('%',#{searchKey}),'%') or" +
//            " mobile like concat(concat('%',#{searchKey}),'%') or" +
//            " email like concat(concat('%',#{searchKey}),'%') or" +
//            " nickname like concat(concat('%',#{searchKey}),'%')")
    @Select("select count(1) as count from user where " +
            " username like '%' || #{searchKey} || '%' or" +
            " mobile like '%' || #{searchKey} || '%' or" +
            " email like '%' || #{searchKey} || '%' or" +
            " nickname like '%' || #{searchKey} || '%'")
    int getSearchUserCount(@Param("searchKey") String searchKey);

    @Select("select * from user where " +
            "username like '%' || #{searchKey} || '%' or " +
            "mobile like '%' || #{searchKey} || '%' or " +
            "email like '%' || #{searchKey} || '%' or " +
            "nickname like '%' || #{searchKey} || '%' " +
            "limit #{offset},#{limit}")
    List<User> getSearchPagedUsers(@Param("offset")int offset, @Param("limit") int limit, @Param("searchKey")String searchKey);

    @Select("select * from user where username=#{username} or mobile=#{mobile} or email=#{email} limit 0,1")
    User getExistUser(@Param("username") String username, @Param("mobile") String mobile, @Param("email") String email);
}