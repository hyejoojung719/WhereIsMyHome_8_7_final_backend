package com.ssafy.house.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.dto.Bus;
import com.ssafy.house.dto.School;
import com.ssafy.house.model.mapper.InfraMapper;

@Service
public class InfraServiceImpl implements InfraService{

	@Autowired
	InfraMapper infraMapper;

	@Override
	public List<School> getCurSchool(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return infraMapper.getCurSchool(map);
	}

	@Override
	public List<Bus> getCurBus(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return infraMapper.getCurBus(map);
	}

}
