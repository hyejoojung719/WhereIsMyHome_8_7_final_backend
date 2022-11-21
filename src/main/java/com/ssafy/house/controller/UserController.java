package com.ssafy.house.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.house.dto.User;
import com.ssafy.house.model.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/users")
@Slf4j
@CrossOrigin("http://localhost:8080")
public class UserController {
	@Autowired
	UserService userService;
	
	final static int EXPIRE_MINUTES = 30; //30분 간격으로 재로그인 필요
	final static String SECRET_KEY = "CARR0TCHEE2ECAK3@@E";

	// **************************** Fetch Method ******************************
	// 로그인 => 회원인지 체크 후 세션 처리
	@ResponseBody
	@PostMapping(value = "/signIn")
	public ResponseEntity<?> signIn(@RequestBody User user) throws SQLException, UnsupportedEncodingException {
		log.debug("signIn() 메소드 요청");
		log.debug("id : {} pwd : {}", user.getUser_id(), user.getUser_password());

		boolean check = userService.signIn(user);

		if (check) {
			
			String token = Jwts.builder()
					//Header
					.setHeaderParam("typ", "JWT")
					.setHeaderParam("alg", "HS256")
					//Payload
					.claim("userId", user.getUser_id())
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
					//signature
					.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8"))
					.compact();
			
			log.debug("발급된 토큰 : {}", token);
			Map<String, String> result = new HashMap<>();
			result.put("token", token);
					
//			session.setAttribute("userId", user.getUser_id());
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		} else {

			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 비밀번호 찾기
	@ResponseBody
	@PostMapping(value = "/findPwd")
	public ResponseEntity<?> findpwd(@RequestBody User user) throws SQLException {
		log.debug("findpwd() 메소드 요청");

		User selectUser = userService.selectUserInfo(user.getUser_id());
		log.debug("selectUser : {}", selectUser.toString());
		if (selectUser != null) {
			log.debug("user : {}", user.toString());
			if (selectUser.getUser_name().equals(user.getUser_name())) {
				return new ResponseEntity<String>(selectUser.getUser_password(), HttpStatus.OK);
			}
		}

		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 회원 가입 => 중복된 회원인지 체크, db에 회원 정보 등록
	@ResponseBody
	@PostMapping
	public ResponseEntity<?> signUp(Model model, @RequestBody User user) throws SQLException {
		log.debug("signUp() 메소드 요청");
		log.debug("user : {}", user.toString());
		int cnt = userService.insertUser(user);

		if (cnt == 1)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 아이디 중복 체크
	@GetMapping(value = "/check")
	@ResponseBody
	public ResponseEntity<?> idCheck(Model model, @RequestParam String user_id) throws SQLException {
		log.debug("idCheck() 메소드 요청");
		log.debug("user_id : {}", user_id);
		int cnt = userService.idCheck(user_id);

		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}


	// 마이페이지 이동 => 회원 정보 가져오기
	@ResponseBody
	@RequestMapping(value = "/myPage")
	public ResponseEntity<?> myPage(Model model, @RequestHeader(value="access-token") String token) throws SQLException, ParseException {
		log.debug("myPage() 메소드 요청");
		String decodedToken = new String(Base64.getDecoder().decode(token.split("\\.")[1]));
		
		//JSONParser 생성
		JSONParser jsonParser = new JSONParser();
		//JSONParser를 통해 JSONObject 생성
		JSONObject jsonObject = (JSONObject) jsonParser.parse(decodedToken);
		//userId 값 추출
		String id = (String) jsonObject.get("userId");

		User user = userService.selectUserInfo(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	// 회원 정보 업데이트
	@ResponseBody
	@PutMapping(value = "/updateUser")
	public ResponseEntity<?> updateUser(Model model, @RequestBody User user) throws SQLException {
		log.debug("updateUser() 메소드 요청");
		log.debug("user : {}", user.toString());
		int cnt = userService.updateUser(user);
		
		if(cnt == 1) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 회원 탈퇴 => db에서 지우고 세션 지우기
	@DeleteMapping(value = "/deleteUser")
	public ResponseEntity<?> deleteUser(HttpSession session, @RequestBody Map<String, String> map) throws SQLException {
		log.debug("deleteUser() 메소드 요청");
		log.debug("user_id : {}", map.get("user_id"));
		String user_id = map.get("user_id");
		int cnt = userService.deleteUser(user_id);
		
		if(cnt == 1) {
			session.removeAttribute("userId");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
