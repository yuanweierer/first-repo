package com.jason.didiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String phone;
    private String nickname;
    private String sex;
    private int type;
    private float balance;

    @JsonIgnore
    private String password;
}
