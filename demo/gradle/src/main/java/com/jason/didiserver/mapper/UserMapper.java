package com.jason.didiserver.mapper;

import com.jason.didiserver.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    public User findById(Integer id);

    //根据电话查询用户
    @Select("select * from user where phone = #{phone}")
    User findByPhone(String phone);

    //添加
    @Insert("insert into user(nickname, phone, password, type)" +
            "values (#{nickname},#{phone},#{password},#{type})")
    void add(String nickname, String phone, String password, int type);

    //更新
    @Update("update user set nickname=#{nickname},balance=#{balance},sex=#{sex},password=#{password},phone=#{phone} where id=#{id}")
    void update(User u);
}
