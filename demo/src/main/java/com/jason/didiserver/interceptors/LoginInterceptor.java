package com.jason.didiserver.interceptors;

import com.jason.didiserver.mapper.JwtMapper;
import com.jason.didiserver.pojo.Result;
import com.jason.didiserver.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtMapper jwtMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        //令牌验证
        String token = request.getHeader("Authorization");

        //验证token
        try {
            //从数据库中获取相同的token
            String dbToken = jwtMapper.get(token);
            if (dbToken == null) {
                //已经失效了
                throw new RuntimeException();
            }

            Map<String, Object> claims = JWTUtil.parseToken(token);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
