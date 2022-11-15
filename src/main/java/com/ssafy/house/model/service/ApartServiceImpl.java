package com.ssafy.house.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.dto.Apart;
import com.ssafy.house.model.mapper.ApartMapper;

@Service
public class ApartServiceImpl implements ApartService{

	@Autowired
	ApartMapper apartMapper;

	// 아파트 정보
	@Override
	public List<Apart> getApartInfo(HashMap<String, Object> map) throws Exception {
		return apartMapper.getApartInfo(map);
	}

	// 아파트 찜목록 가져오기
	@Override
	public List<Apart> getMyApartInfo(HashMap<String, Object> map) throws Exception {
		return apartMapper.getMyApartInfo(map);
	}

	// 찜 추가하기
	@Override
	public int insertMyApart(HashMap<String, Object> map) throws Exception {
		return apartMapper.insertMyApart(map);
	}

	// 찜 제거하기
	@Override
	public int delMyApart(HashMap<String, Object> map) throws Exception {
		return apartMapper.delMyApart(map);
	}
	
}
