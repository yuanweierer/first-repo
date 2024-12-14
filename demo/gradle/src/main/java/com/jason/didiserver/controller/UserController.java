package com.jason.didiserver.controller;

import com.jason.didiserver.pojo.Result;
import com.jason.didiserver.pojo.User;
import com.jason.didiserver.pojo.UserInfo;
import com.jason.didiserver.service.UserService;
import com.jason.didiserver.service.JwtService;
import com.jason.didiserver.utils.JWTUtil;
import com.jason.didiserver.utils.MD5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("findById")
    public User findById(Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/register")
    public Result register(String nickname, String phone, @Pattern(regexp = "^\\S{5,16}$") String password, int type) {
        //查询用户
        User u = userService.findByPhone(phone);
        if (u == null) {
            //没有占用
            //注册
            userService.register(nickname, phone, password, type);
            return Result.success();
        } else {
            //占用
            return Result.error("手机号码已注册");
        }
    }

    @PostMapping("/login")
    public Result<UserInfo> login(String phone, @Pattern(regexp = "^\\S{5,16}$") String password, int type) {
        //根据用户名查询用户
        User loginUser = userService.findByPhone(phone);
        //判断该用户是否存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }
        UserInfo user = new UserInfo();
        user.setId(loginUser.getId());
        user.setNickName(loginUser.getNickname());
        user.setType(loginUser.getType());
        user.setSex(loginUser.getSex());
        user.setBalance(loginUser.getBalance());
        user.setPhone(loginUser.getPhone());

        if (loginUser.getType() == type) {
            //判断密码是否正确
            if (MD5Util.convertMD5(password).equals(loginUser.getPassword())) {
                //登录成功
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", loginUser.getId());
                claims.put("phone", loginUser.getPhone());
                String token = JWTUtil.genToken(claims);
                jwtService.login(loginUser.getId(), token);
                user.setToken(token);
                return Result.success(user);
            }
            return Result.error("密码错误");
        }
        return Result.error("类型错误");
    }

    @GetMapping("/islogin")
    public Result islogin(@RequestHeader(name = "Authorization") String token) {
        try {
            //根据jwt库判断是否登录
            if (!jwtService.findByToken(token)) return Result.error("未登录");
        } catch (Exception e) {
            return Result.error("未登录");
        }
        return Result.success(true);
    }

    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "Authorization") String token) {
        try {
            Map<String, Object> map = JWTUtil.parseToken(token);
            int id = (Integer) map.get("id");
            jwtService.remove(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("已退出");
        }
    }

    @GetMapping("/pay")
    public Result addbalance(@RequestHeader(name = "Authorization") String token, String money) {
        //根据电话号码支付
        Map<String, Object> map = JWTUtil.parseToken(token);
        String phone = (String) map.get("phone");

        User u = userService.findByPhone(phone);
        u.setBalance(u.getBalance() + Float.parseFloat(money));
        userService.update(u);
        return Result.success(true);
    }

    @GetMapping("/userinfo")
    public Result<UserInfo> findUserByid(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> map = JWTUtil.parseToken(token);
        int id = (Integer) map.get("id");

        User loginUser = userService.findById(id);
        UserInfo user = new UserInfo();
        user.setId(loginUser.getId());
        user.setNickName(loginUser.getNickname());
        user.setType(loginUser.getType());
        user.setSex(loginUser.getSex());
        user.setBalance(loginUser.getBalance());
        user.setPhone(loginUser.getPhone());
        user.setToken(token);
        return Result.success(user);
    }
}
