package com.jason.didiserver.service.impl;

import com.jason.didiserver.mapper.OrderMapper;
import com.jason.didiserver.pojo.Order;
import com.jason.didiserver.pojo.OrderItem;
import com.jason.didiserver.pojo.User;
import com.jason.didiserver.service.OrderService;
import com.jason.didiserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<OrderItem> getOrderitems(Integer id, Integer type) {
        if (type == 0)//货主查询订单
        {
            List<OrderItem> ls = orderMapper.findOrdersByOwnerId(id);
            return ls;
        } else {//司机查询订单
            List<OrderItem> ls = orderMapper.findOrdersByDriverId(id);
            return ls;
        }
    }

    @Override
    public void creatOrder(Order order) {

        User owner = userService.findById(order.getOwnerId());
        User driver = userService.findById(order.getDriverId());

        //付款
        owner.setBalance(owner.getBalance() - order.getPrice());
        userService.update(owner);
        //收款
        driver.setBalance(driver.getBalance() + order.getPrice());
        userService.update(driver);

        //生成订单
        orderMapper.insert(order);
    }
}
