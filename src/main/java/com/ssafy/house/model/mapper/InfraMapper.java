package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.house.dto.Bus;
import com.ssafy.house.dto.School;

public interface InfraMapper {

	List<School> getCurSchool(HashMap<String, Object> map) throws SQLException;

	List<Bus> getCurBus(HashMap<String, Object> map) throws SQLException;

}
