package com.jason.didiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Path {
    private String location;
    private float carriage;
    private Integer userId;
    private Integer id;
}
