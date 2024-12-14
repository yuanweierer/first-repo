package com.jason.didiserver.service.impl;

import com.jason.didiserver.mapper.JwtMapper;
import com.jason.didiserver.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private JwtMapper jwtMapper;

    @Override
    public void login(Integer id, String token) {
        jwtMapper.insert(id,token);
    }

    @Override
    public void remove(int id) {
        jwtMapper.delete(id);
    }

    @Override
    public boolean findByToken(String token) {
        String t = jwtMapper.get(token);
        if(t.isEmpty()) return false;
        else return true;
    }
}
