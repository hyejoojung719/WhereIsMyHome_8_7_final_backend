package com.ssafy.house.model.service;

import java.util.List;

import com.ssafy.house.dto.Dong;

public interface DongService {

	List<Dong> getSidoName() throws Exception;

	List<Dong> getGugunName(String sidoCode) throws Exception;

	List<Dong> getDongName(String dongCode) throws Exception;

}
