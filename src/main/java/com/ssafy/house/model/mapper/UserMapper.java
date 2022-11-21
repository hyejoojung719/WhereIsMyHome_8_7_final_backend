package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.house.dto.User;

public interface UserMapper {
	int insertUser(User user) throws SQLException;
	
	int insertRole(User user) throws SQLException;

	int signIn(User user) throws SQLException;

	User selectUserInfo(Map<String, Object> map) throws SQLException;

	int updateUser(User user) throws SQLException;

	int deleteUser(Map<String, Object> map) throws SQLException;

	int idCheck(Map<String, Object> map) throws SQLException;
}
