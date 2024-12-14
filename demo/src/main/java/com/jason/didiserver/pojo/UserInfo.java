package com.jason.didiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	private int id;
	private String phone;
	private String nickName;
	private String sex;
	private int type;
	private float balance;
	private String token;
}
