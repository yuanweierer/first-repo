package com.jason.didiserver.mapper;

import com.jason.didiserver.pojo.Order;
import com.jason.didiserver.pojo.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select orders.id as id,start,end,price,user.id as user_id,nickname,phone from orders,user "+
            "where orders.driver_id=user.id and orders.owner_id=#{id}")
    List<OrderItem> findOrdersByOwnerId(Integer id);

    @Select("select orders.id as id,start,end,price,user.id as user_id,nickname,phone from orders,user "+
            "where orders.owner_id=user.id and orders.driver_id=#{id}")
    List<OrderItem> findOrdersByDriverId(Integer id);

    @Insert("insert into orders(owner_id,driver_id,price,start,end)"+
            "values (#{ownerId},#{driverId},#{price},#{start},#{end})")
    void insert(Order order);
}
