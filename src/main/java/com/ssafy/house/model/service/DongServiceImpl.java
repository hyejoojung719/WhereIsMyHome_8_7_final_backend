package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.dto.Dong;
import com.ssafy.house.model.mapper.DongMapper;

@Service
public class DongServiceImpl implements DongService {

	@Autowired
	DongMapper dongMapper;

	@Override
	public List<Dong> getSidoName() throws Exception {
		return dongMapper.getSidoName();
	}

	@Override
	public List<Dong> getGugunName(String sidoCode) throws SQLException {
		return dongMapper.getGugunName(sidoCode);
	}

	@Override
	public List<Dong> getDongName(String dongCode) throws Exception {
		return dongMapper.getDongName(dongCode);
	}

	// 아파트 주소 가져오기
	@Override
	public Dong getAddress(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return dongMapper.getAddress(map);
	}
}
