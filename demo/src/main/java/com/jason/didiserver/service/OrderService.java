package com.jason.didiserver.service;

import com.jason.didiserver.pojo.Order;
import com.jason.didiserver.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    List<OrderItem> getOrderitems(Integer id, Integer type);

    void creatOrder(Order order);
}
