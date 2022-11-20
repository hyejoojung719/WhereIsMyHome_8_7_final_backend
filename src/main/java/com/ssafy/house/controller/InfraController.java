package com.ssafy.house.controller;

import java.util.ArrayList;
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

import com.ssafy.house.dto.Bus;
import com.ssafy.house.dto.School;
import com.ssafy.house.model.service.InfraService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/infra")
@Slf4j
public class InfraController {

	@Autowired
	InfraService infraService;

	@Autowired
	ServletContext servletContext;

	// 2km 반경 안에 있는 애들 가져오기 
	@GetMapping("/school")
	public ResponseEntity<?> getCurSchool(@RequestParam("curlat") String curlat, @RequestParam("curlng") String curlng) {

		log.debug("getCurSchool() 메소드 실행 ");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("curlat", curlat);
		map.put("curlng", curlng);

		try {
			List<School> list = new ArrayList<>();

			// 전체 학교 정보 
			list = infraService.getCurSchool(map);

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<School>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 1km 반경 안에 있는 애들 가져오기 
	@GetMapping("/bus")
	public ResponseEntity<?> getCurBus(@RequestParam("curlat") String curlat, @RequestParam("curlng") String curlng) {

		log.debug("getCurBus() 메소드 실행 ");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("curlat", curlat);
		map.put("curlng", curlng);

		try {
			List<Bus> list = new ArrayList<>();

			// 전체 학교 정보 
			list = infraService.getCurBus(map);

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Bus>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}


	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
