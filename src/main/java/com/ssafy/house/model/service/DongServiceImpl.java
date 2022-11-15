package com.ssafy.house.model.service;

import java.sql.SQLException;
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
	
}
