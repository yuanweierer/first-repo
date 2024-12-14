package com.jason.didiserver.service;

import com.jason.didiserver.pojo.User;

public interface UserService {

    public User findById(Integer id);

    //根据电话号码查询用户
    User findByPhone(String phone);


    //注册
    void register(String nickname, String phone, String password, int type);

    void update(User u);
}
