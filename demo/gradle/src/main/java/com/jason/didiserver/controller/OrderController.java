package com.jason.didiserver.controller;

import com.jason.didiserver.pojo.Order;
import com.jason.didiserver.pojo.OrderItem;
import com.jason.didiserver.pojo.Result;
import com.jason.didiserver.pojo.User;
import com.jason.didiserver.service.OrderService;
import com.jason.didiserver.service.UserService;
import com.jason.didiserver.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    //查询所有订单
    @GetMapping("/orderitem")
    private Result<List<OrderItem>> getOrderitems(@RequestHeader(name = "Authorization") String token) {
        //找到当前登录用户的type
        Map<String, Object> map = JWTUtil.parseToken(token);
        String phone = (String) map.get("phone");
        User u = userService.findByPhone(phone);
        Integer type = u.getType();
        Integer id = u.getId();

        try {
            //不同的type不同的查找
            List<OrderItem> ls = orderService.getOrderitems(id, type);
            return Result.success(ls);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("未找到订单");
        }
    }

    //创建订单
    @PostMapping("/createorder")
    private Result creatOrder(@RequestHeader(name = "Authorization") String token, @RequestBody Order order) {
        Map<String, Object> map = JWTUtil.parseToken(token);
        String phone = (String) map.get("phone");
        User u = userService.findByPhone(phone);
        order.setOwnerId(u.getId());
        if (u.getBalance() >= order.getPrice()) {
            //查看钱够不够支付订单
            try {
                orderService.creatOrder(order);
            }catch (Exception e){
                e.printStackTrace();
                Result.error("出现错误");
            }
        } else {
            return Result.error("余额不足");
        }
        return Result.success();
    }
}
