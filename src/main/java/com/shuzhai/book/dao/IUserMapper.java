package com.shuzhai.book.dao;


import com.shuzhai.book.common.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface IUserMapper {

    @Insert("insert into book_user(username,password,email,phone,role,sex,face,create_time,update_time) " +
            "values(#{username},#{password},#{email},#{phone},#{role},#{sex},#{face},#{createTime},#{updateTime})")
    int insertUser(User user);

    @Select("select id,username,email,phone,role,sex,face,create_time from book_user where username=#{username}")
    User selectByName(String username);

    @Select("select password from book_user where username=#{username}")
    String selectPwdByName(String username);

    @Select("select * from book_user where phone=#{phone}")
    User selectByPhone(String phone);
}
