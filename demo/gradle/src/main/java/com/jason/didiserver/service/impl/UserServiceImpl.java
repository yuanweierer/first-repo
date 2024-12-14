package com.jason.didiserver.service.impl;

import com.jason.didiserver.mapper.UserMapper;
import com.jason.didiserver.pojo.User;
import com.jason.didiserver.service.UserService;
import com.jason.didiserver.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByPhone(String phone) {
        User u = userMapper.findByPhone(phone);
        return u;
    }

    @Override
    public void register(String nickname, String phone, String password, int type) {
        //加密
        String md5string = MD5Util.convertMD5(password);
        //添加
        userMapper.add(nickname, phone, md5string, type);
    }

    @Override
    public void update(User u) {
        userMapper.update(u);
    }
}
