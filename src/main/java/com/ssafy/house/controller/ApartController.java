package com.ssafy.house.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.house.dto.Apart;
import com.ssafy.house.dto.Board;
import com.ssafy.house.model.service.ApartService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/apart")
@Slf4j
public class ApartController {

	@Autowired
	ApartService apartService;

	@Autowired
	ServletContext servletContext;

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
	@PostMapping("/ckApart")
	public void addCkApart(@RequestBody String[] ckList, HttpServletRequest request) throws Exception {

		log.debug("addCkApart() 메소드 실행 ");

		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("userId");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);

		// 현재 찜 목록
		List<Apart> list = apartService.getMyApartInfo(map);

		System.out.println("현재 저장되어있는 찜 목록");
		for (Apart apart : list) {
			System.out.print(apart.getAptCode() + " : ");
		}
		System.out.println();

		System.out.println("내가 찜한 것까지 포함한 찜 목록");
		for (String apart : ckList) {
			System.out.print(apart + " : ");
		}
		System.out.println();


		// 아예 저장된 게 없으면 그냥 추가
		if(list.size()==0) {
			for (String ac : ckList) {
				HashMap<String, Object> map2 = new HashMap<String, Object>();
				map2.put("user_id", user_id);
				map2.put("ac", ac);

				int cnt = apartService.insertMyApart(map2);
			}
		}else {

			// 관심 지역 추가
			for (String ac : ckList) {
				boolean flag = false;
				for (Apart apart : list) {
					if(ac.equals(apart.getAptCode())) {
						// 현재 저장되어 있는 찜 목록에 내가 찜한 것 까지 포함한 찜 목록이 있다면 
						flag = true;
					}
				}
				if(!flag) {
					// 현재 저장된 찜 목록에 없는 것이므로 추가하기
					HashMap<String, Object> map2 = new HashMap<String, Object>();
					map2.put("user_id", user_id);
					map2.put("ac", ac);

					System.out.println(ac+" 추가");
					int cnt = apartService.insertMyApart(map2);
				}
			}

			// 관심 지역 삭제
			for (Apart apart : list) {
				boolean flag = false;
				for (String ac : ckList) {
					if(ac.equals(apart.getAptCode())) {
						// 현재 저장되어 있는 찜 목록중에 내가 찜한 것 까지 포함한 찜 목록에 있다면
						flag = true;
					}
				}
				if(!flag) {
					// 현재 저장된 찜목록에 있는게 내가 찜한 목록에 없으므로 삭제
					HashMap<String, Object> map2 = new HashMap<String, Object>();
					map2.put("user_id", user_id);
					map2.put("ac", apart.getAptCode());

					System.out.println(apart.getAptCode()+" 삭제");
					int cnt = apartService.delMyApart(map2);
				}
			}

		}

	}

	// 관심 아파트 목록 불러오기
	@GetMapping
	public ResponseEntity<?> listMyApart(HttpSession session) throws Exception{
		log.debug("listMyApart() 메소드 실행 ");

		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String userId = (String) session.getAttribute("userId");
			map.put("user_id", userId);
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
