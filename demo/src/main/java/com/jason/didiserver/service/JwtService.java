package com.jason.didiserver.service;

public interface JwtService {
    void login(Integer id, String token);

    void remove(int id);

    boolean findByToken(String token);
}
