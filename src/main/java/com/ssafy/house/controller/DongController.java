package com.ssafy.house.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
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
	private ResponseEntity<?> getGugunName(@RequestParam("regcode") String regcode) throws SQLException, IOException {

		log.debug("getGugunName() 메소드 실행 ");

		String sidoCode = regcode.split("\\*")[0];
		
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
	private ResponseEntity<?> getDongName(@RequestParam("regcode") String regcode) throws SQLException, IOException {

		log.debug("getDongName() 메소드 실행 ");

		String dongCode = regcode.split("\\*")[0];
		
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
