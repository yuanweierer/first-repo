package com.jason.didiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PathInfo {
    private int id;
    private String nickname;
    private String phone;
    private float price;
}
