package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.HashMap;
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
		return userMapper.insertUser(user);
	}

	@Override
	public boolean signIn(User user) throws SQLException {
		int cnt = userMapper.signIn(user);
		if(cnt == 1) return true;
		else return false;
	}

	@Override
	public User selectUserInfo(String user_id) throws SQLException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		
		return userMapper.selectUserInfo(map);
	}

	@Override
	public int updateUser(User user) throws SQLException {
		return userMapper.updateUser(user);
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
