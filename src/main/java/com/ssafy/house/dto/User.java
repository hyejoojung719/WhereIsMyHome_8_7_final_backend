package com.ssafy.house.dto;

import lombok.Data;

@Data
public class User {
	private String user_id;
	private String user_password;
	private String user_name;
	private String user_birth;
	private String user_role;
}
