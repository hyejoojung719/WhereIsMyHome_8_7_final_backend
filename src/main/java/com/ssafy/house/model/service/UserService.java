package com.ssafy.house.model.service;

import java.sql.SQLException;

import com.ssafy.house.dto.User;

public interface UserService {
	int insertUser(User user) throws SQLException;

	boolean signIn(User user) throws SQLException;

	User selectUserInfo(String user_id) throws SQLException;
	
	User selectUserInfoNoPassword(String user_id) throws SQLException;

	int updateUser(User user) throws SQLException;
	
	int updatePassword(User user) throws SQLException;

	int deleteUser(String user_id) throws SQLException;

	int idCheck(String user_id) throws SQLException;
}
