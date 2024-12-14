package com.jason.didiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int ownerId;
    private int driverId;
    private int id;
    private String start;
    private String end;
    private float price;
}
