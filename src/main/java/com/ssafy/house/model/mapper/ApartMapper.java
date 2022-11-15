package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.dto.Apart;

@Mapper
public interface ApartMapper {

	List<Apart> getApartInfo(HashMap<String, Object> map) throws SQLException;

	List<Apart> getMyApartInfo(HashMap<String, Object> map) throws SQLException;
	
	int insertMyApart(HashMap<String, Object> map) throws SQLException;
	
	int delMyApart(HashMap<String, Object> map) throws SQLException;
	

}