package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.dto.User;
import com.ssafy.house.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public int insertUser(User user) throws SQLException {
		int cnt_user = userMapper.insertUser(user);
		int cnt_role = userMapper.insertRole(user);
		
		if(cnt_user == 1 && cnt_role == 1) return 1;
		else return 0;
	}

	@Override
	public boolean signIn(User user) throws SQLException {
		int cnt = userMapper.signIn(user);
		if(cnt == 1) return true;
		else return false;
	}

	@Override
	public List<Map<String, String>> selectUserId(User user) throws SQLException {
		return userMapper.selectUserId(user);
	}
	
	@Override
	public User selectUserInfo(String user_id) throws SQLException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		
		return userMapper.selectUserInfo(map);
	}
	
	@Override
	public User selectUserInfoNoPassword(String user_id) throws SQLException {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		
		return userMapper.selectUserInfoNoPassword(map);
	}
	
	@Override
	public int updateUser(User user) throws SQLException {
		return userMapper.updateUser(user);
	}
	
	@Override
	public int updatePassword(User user) throws SQLException {
		return userMapper.updatePassword(user);
	}

	@Override
	public int deleteUser(String user_id) throws SQLException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		
		int cnt = userMapper.deleteUser(map);
		return cnt;
	}

	@Override
	public int idCheck(String user_id) throws SQLException {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		
		int cnt = userMapper.idCheck(map);
		return cnt;
	}
}
