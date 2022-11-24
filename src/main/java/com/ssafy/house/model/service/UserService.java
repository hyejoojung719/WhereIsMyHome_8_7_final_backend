package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.house.dto.User;

public interface UserService {
	int insertUser(User user) throws SQLException;

	boolean signIn(User user) throws SQLException;
	
	List<Map<String, String>> selectUserId(User user) throws SQLException;

	User selectUserInfo(String user_id) throws SQLException;
	
	User selectUserInfoNoPassword(String user_id) throws SQLException;

	int updateUser(User user) throws SQLException;
	
	int updatePassword(User user) throws SQLException;

	int deleteUser(String user_id) throws SQLException;

	int idCheck(String user_id) throws SQLException;
	
	String findPassword(User user) throws SQLException;
	
	Boolean sendEmail(User user) throws Exception;

	List<User> selectUserListAll() throws SQLException;

	int deleteUserList(List<User> userList) throws SQLException;
}
