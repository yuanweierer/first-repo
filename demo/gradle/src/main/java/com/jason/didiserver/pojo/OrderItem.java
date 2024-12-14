package com.jason.didiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private int userId;
    private int id;
    private String nickname;
    private String start;
    private String end;
    private String phone;
    private float price;
}
