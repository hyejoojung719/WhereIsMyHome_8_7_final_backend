package com.ssafy.house.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.dto.Apart;
import com.ssafy.house.model.service.ApartService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/apart")
@Slf4j
public class ApartController {

	@Autowired
	ApartService apartService;

	@Autowired
	ServletContext servletContext;
	
	// 아파트 거래금액 가져오기(년도별 최대 금액)
	@GetMapping("/amount")
	public ResponseEntity<?> getAmount(@RequestParam("aptCode") String aptCode) {

		log.debug("getAmount() 메소드 실행 ");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("aptCode", aptCode);


		try {
			List<Apart> list = new ArrayList<Apart>();
			
			// 전체 아파트 정보 
			list = apartService.getAmount(map);

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Apart>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	// 아파트 상세정보 가져오기 
	@GetMapping("/detailApart")
	public ResponseEntity<?> getDetailApart(@RequestParam("aptCode") String aptCode) {

		log.debug("getDetailApart() 메소드 실행 ");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("aptCode", aptCode);


		try {
			List<Apart> list = new ArrayList<Apart>();
			
			// 전체 아파트 정보 
			list = apartService.getDetailApart(map);

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Apart>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 현재 위치안에 있는 아파트 목록 불러오기 
	@GetMapping("/curApart")
	public ResponseEntity<?> getCurApart(@RequestParam("curlat") String curlat, @RequestParam("curlng") String curlng) {

		log.debug("getCurApart() 메소드 실행 ");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("curlat", curlat);
		map.put("curlng", curlng);


		try {
			List<Apart> list = new ArrayList<Apart>();

			// 전체 아파트 정보 
			list = apartService.getCurApart(map);

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Apart>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 아파트 검색 기능
	@GetMapping("/search")
	public ResponseEntity<?> searchApart(@RequestParam("keyword") String keyword) {

		log.debug("searchApart() 메소드 실행 ");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);

		try {
			List<Apart> list = new ArrayList<Apart>();

			// 전체 아파트 정보 
			list = apartService.searchApart(map);

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Apart>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}


	// 아파트 정보 불러오기
	@GetMapping("/apartInfo")
	public ResponseEntity<?> getApartInfo(@RequestParam("dongCode") String dongcode,
			//			@RequestParam("year") String year,
			//			@RequestParam("month") String month,
			HttpServletRequest request) {

		log.debug("getApartInfo() 메소드 실행 ");

		// getApartInfo 매개변수 위한 map
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("dongcode", dongcode);
		//		map1.put("year", year);
		//		map1.put("month", month);

		// 찜목록 가져오기
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("userId");

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("user_id", user_id);

		try {
			List<Apart>[] list = new List[2];
			for (int i = 0; i < list.length; i++) {
				list[i]  = new ArrayList<Apart>();
			}

			// 전체 아파트 정보 
			list[0] = apartService.getApartInfo(map1);

			// 찜 목록 가져오기 
			list[1] = apartService.getMyApartInfo(map2);

			if(list[0] != null && !list[0].isEmpty()) {
				return new ResponseEntity<List<Apart>[]>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 관심 아파트 등록하기
	@PostMapping("/myHouse")
	public void insertMyApart(@RequestBody Apart house, @RequestHeader(value="access-token") String token) throws Exception {

		log.debug("addCkApart() 메소드 실행 ");


		String decodedToken = new String(Base64.getDecoder().decode(token.split("\\.")[1]));

		//JSONParser 생성
		JSONParser jsonParser = new JSONParser();
		//JSONParser를 통해 JSONObject 생성
		JSONObject jsonObject = (JSONObject) jsonParser.parse(decodedToken);
		//userId 값 추출
		String user_id = (String) jsonObject.get("userId");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);


		// 현재 찜 목록
		List<Apart> list = apartService.getMyApartInfo(map);

		// 아예 저장된 게 없으면 그냥 추가
		if(list.size()==0) {
			HashMap<String, Object> map2 = new HashMap<String, Object>();
			map2.put("user_id", user_id);
			map2.put("aptCode", house.getAptCode());

			apartService.insertMyApart(map2);
		}else {
			// 관심 지역 추가
			boolean flag = false; 
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getAptCode() == house.getAptCode()) {
					flag = true;
				}
			}

			// 존재하지 않는 관심 지역 정보면 
			if(!flag) {
				HashMap<String, Object> map2 = new HashMap<String, Object>();
				map2.put("user_id", user_id);
				map2.put("aptCode", house.getAptCode());

				apartService.insertMyApart(map2);
			}else {
				// 이미 존재하는 관심 지역 정보면 삭제한다. 
				HashMap<String, Object> map2 = new HashMap<String, Object>();
				map2.put("user_id", user_id);
				map2.put("ac", house.getAptCode());

				apartService.delMyApart(map2);
			}

		}

	}

	// 관심 아파트 목록 불러오기
	@GetMapping("/myHouse")
	public ResponseEntity<?> listMyApart(@RequestHeader(value="access-token") String token) throws Exception{
		log.debug("listMyApart() 메소드 실행 ");

		String decodedToken = new String(Base64.getDecoder().decode(token.split("\\.")[1]));

		//JSONParser 생성
		JSONParser jsonParser = new JSONParser();
		//JSONParser를 통해 JSONObject 생성
		JSONObject jsonObject = (JSONObject) jsonParser.parse(decodedToken);
		//userId 값 추출
		String user_id = (String) jsonObject.get("userId");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);	

		log.debug("user_id 정보 {} ", user_id);

		try {
			List<Apart> list = apartService.getMyApartInfo(map);

			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Apart>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 관심 아파트 삭제하기
	@DeleteMapping("{aptCode}")
	public void deleteMyApart(@PathVariable("aptCode") String aptCode, HttpSession session) throws Exception {

		log.debug("deleteMyApart() 메소드 실행"); 

		HashMap<String, Object> map = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		map.put("user_id", userId);
		map.put("ac", aptCode);

		apartService.delMyApart(map);
	}

	// alt + shift + m 눌러서 중복된 코드를 대체 가능
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
