package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.dto.Dong;

@Mapper
public interface DongMapper {
	
	List<Dong> getSidoName() throws SQLException;
	
	List<Dong> getGugunName(String sidoCode) throws SQLException;

	List<Dong> getDongName(String dongCode) throws SQLException;

}
