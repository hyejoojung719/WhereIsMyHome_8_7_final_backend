package com.ssafy.house.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.house.dto.Apart;

public interface ApartService {

	List<Apart> getApartInfo(HashMap<String, Object> map) throws Exception;

	List<Apart> getMyApartInfo(HashMap<String, Object> map) throws Exception;

	int insertMyApart(HashMap<String, Object> map) throws Exception;

	int delMyApart(HashMap<String, Object> map) throws Exception;

	List<Apart> getCurApart(HashMap<String, Object> map) throws Exception;

	List<Apart> searchApart(HashMap<String, Object> map) throws Exception;

	Apart getDetailApart(HashMap<String, Object> map) throws Exception;

	

}
