package com.ssafy.house.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.house.dto.Bus;
import com.ssafy.house.dto.School;

public interface InfraService {

	List<School> getCurSchool(HashMap<String, Object> map) throws Exception;

	List<Bus> getCurBus(HashMap<String, Object> map) throws Exception;

}
