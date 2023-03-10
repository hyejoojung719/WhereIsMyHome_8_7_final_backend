package com.ssafy.house.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.house.dto.Dong;
import com.ssafy.house.model.service.DongService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/dong")
@Slf4j
public class DongController {

	@Autowired
	DongService dongService;

	@Autowired
	ServletContext servletContext;

	// 주소 가져오기
	@GetMapping("/addr")
	public ResponseEntity<?> getAddress(@RequestParam("dongCode") String dongCode) {

		log.debug("getAddress() 메소드 실행 ");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dongcode", dongCode);


		try {
			// 전체 아파트 정보 
			Dong dong = dongService.getAddress(map);

			if(dong!=null) {
				return new ResponseEntity<Dong>(dong, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 시도 정보 가져오기
	@GetMapping("/sido")
	private ResponseEntity<?> getSidoName() throws SQLException, IOException {

		log.debug("getSidoName() 메소드 실행 ");

		try {
			List<Dong> list = dongService.getSidoName();

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Dong>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}


	// 구군 정보 가져오기
	@GetMapping("/gugun")
	private ResponseEntity<?> getGugunName(@RequestParam("sido") String sido) throws SQLException, IOException {

		log.debug("getGugunName() 메소드 실행 ");


		String sidoCode = sido;

		try {
			List<Dong> list = dongService.getGugunName(sidoCode);

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Dong>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 동 정보 가져오기
	@GetMapping("/dong")
	private ResponseEntity<?> getDongName(@RequestParam("gugun") String gugun) throws SQLException, IOException {

		log.debug("getDongName() 메소드 실행 ");

		String dongCode = gugun;

		try {
			List<Dong> list = dongService.getDongName(dongCode);

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Dong>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}


	// alt + shift + m 눌러서 중복된 코드를 대체 가능
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
