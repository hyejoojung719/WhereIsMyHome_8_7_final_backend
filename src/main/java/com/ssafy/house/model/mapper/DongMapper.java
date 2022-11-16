package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.house.dto.Dong;


public interface DongMapper {
	
	List<Dong> getSidoName() throws SQLException;
	
	List<Dong> getGugunName(String sidoCode) throws SQLException;

	List<Dong> getDongName(String dongCode) throws SQLException;

}
