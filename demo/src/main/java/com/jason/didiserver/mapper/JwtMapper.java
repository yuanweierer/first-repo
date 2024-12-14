package com.jason.didiserver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JwtMapper {

    @Insert("insert into jwt(user_id,token)"+
            "values(#{userId},#{token})")
    void insert(Integer userId, String token);

    @Delete("delete from jwt where user_id=#{id}")
    void delete(Integer id);

    @Select("select token from jwt where token=#{token}")
    String get(String token);
}
