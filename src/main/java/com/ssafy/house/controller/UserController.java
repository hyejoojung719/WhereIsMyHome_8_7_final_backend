package com.ssafy.house.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.house.dto.User;
import com.ssafy.house.model.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/users")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	final static int EXPIRE_MINUTES = 30; // 30분 간격으로 재로그인 필요
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
					// Header
					.setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")
					// Payload
					.claim("userId", user.getUser_id())
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
					// signature
					.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8")).compact();

			log.debug("발급된 토큰 : {}", token);

			User userInfo = userService.selectUserInfoNoPassword(user.getUser_id());

			Map<String, Object> result = new HashMap<>();
			result.put("token", token);
			result.put("userInfo", userInfo);

			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {

			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 아이디 찾기
	@ResponseBody
	@PostMapping(value = "/find")
	public ResponseEntity<?> findid(@RequestBody User user) throws SQLException {
		log.debug("findid() 메소드 요청");

		List<Map<String, String>> selectUserList = userService.selectUserId(user);
		return new ResponseEntity<List<Map<String, String>>>(selectUserList, HttpStatus.OK);
	}

	// 비밀번호 찾기
	@ResponseBody
	@GetMapping(value = "/find")
	public ResponseEntity<?> findpwd(String user_id) throws SQLException {
		log.debug("findpwd() 메소드 요청");

		User selectUser = userService.selectUserInfo(user_id);
		log.debug("selectUser : {}", selectUser.toString());
		if (selectUser == null) {
			return new ResponseEntity<String>("등록되지 않은 이메일입니다.", HttpStatus.OK);
		} else {
			// 임시 비밀번호 생성
			String msg = userService.findPassword(selectUser);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
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
	@GetMapping
	public ResponseEntity<?> myPage(Model model, @RequestHeader(value = "access-token") String token)
			throws SQLException, ParseException {
		log.debug("myPage() 메소드 요청");
		String decodedToken = new String(Base64.getDecoder().decode(token.split("\\.")[1]));

		// JSONParser 생성
		JSONParser jsonParser = new JSONParser();
		// JSONParser를 통해 JSONObject 생성
		JSONObject jsonObject = (JSONObject) jsonParser.parse(decodedToken);
		// userId 값 추출
		String user_id = (String) jsonObject.get("userId");

		User user = userService.selectUserInfoNoPassword(user_id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 회원 정보 업데이트
	@ResponseBody
	@PutMapping
	public ResponseEntity<?> updateUser(Model model, @RequestBody User user) throws SQLException {
		log.debug("updateUser() 메소드 요청");
		log.debug("user : {}", user.toString());
		int cnt = userService.updateUser(user);

		if (cnt == 1) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 비밀번호 업데이트
	@ResponseBody
	@PutMapping("/pass")
	public ResponseEntity<?> updatePassword(@RequestBody User user) throws SQLException {
		log.debug("updatePassword() 메소드 요청");
		log.debug("user : {}", user.toString());
		int cnt = userService.updatePassword(user);

		if (cnt == 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 회원 탈퇴 => db에서 지우고 세션 지우기
	@DeleteMapping
	public ResponseEntity<?> deleteUser(@RequestHeader(value = "access-token") String token)
			throws SQLException, ParseException {
		log.debug("deleteUser() 메소드 요청");

		String decodedToken = new String(Base64.getDecoder().decode(token.split("\\.")[1]));

		// JSONParser 생성
		JSONParser jsonParser = new JSONParser();
		// JSONParser를 통해 JSONObject 생성
		JSONObject jsonObject = (JSONObject) jsonParser.parse(decodedToken);
		// userId 값 추출
		String user_id = (String) jsonObject.get("userId");

		log.debug("user_id : {}", user_id);
		int cnt = userService.deleteUser(user_id);

		if (cnt == 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 관리자 - 회원 정보 조회
	@ResponseBody
	@PostMapping(value = "/admin/list")
	public ResponseEntity<?> adminUserList(@RequestHeader(value = "access-token") String token, @RequestBody User user)
			throws SQLException, ParseException {
		log.debug("adminUserList() 메소드 요청");
		String user_id = user.getUser_id();
		log.debug("user_id : {}", user_id);
		
		String decodedToken = new String(Base64.getDecoder().decode(token.split("\\.")[1]));

		// JSONParser 생성
		JSONParser jsonParser = new JSONParser();
		// JSONParser를 통해 JSONObject 생성
		JSONObject jsonObject = (JSONObject) jsonParser.parse(decodedToken);
		// userId 값 추출
		String token_user_id = (String) jsonObject.get("userId");

		String user_role = userService.getAuthority(user_id).getUser_role();
		String token_user_role = userService.getAuthority(token_user_id).getUser_role();
		if (user_role.equals("ADMIN") && token_user_role.equals("ADMIN")) {
			List<User> userList = userService.selectUserListAll();

			if (!userList.isEmpty()) {
				return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@PatchMapping(value = "/admin")
	public ResponseEntity<?> adminDeleteUserList(@RequestHeader(value = "access-token") String token,
			@RequestBody HashMap<String, Object> map) throws SQLException, ParseException {
		log.debug("adminDeleteUserList() 메소드 요청");
		
		//타입 Cast 문제로 인해 아래의 형태
		ObjectMapper mapper = new ObjectMapper();
		List<User> userList = mapper.convertValue(map.get("selected"), new TypeReference<List<User>>() {});
		
		log.debug("userList : {}", userList);
		
		String user_id = (String) map.get("user_id");
		
		String decodedToken = new String(Base64.getDecoder().decode(token.split("\\.")[1]));

		// JSONParser 생성
		JSONParser jsonParser = new JSONParser();
		// JSONParser를 통해 JSONObject 생성
		JSONObject jsonObject = (JSONObject) jsonParser.parse(decodedToken);
		// userId 값 추출
		String token_user_id = (String) jsonObject.get("userId");

		String user_role = userService.getAuthority(user_id).getUser_role();
		String token_user_role = userService.getAuthority(token_user_id).getUser_role();
		
		// 회원 자격 검색 후 삭제 진행
		if (user_role.equals("ADMIN") && token_user_role.equals("ADMIN")) {
			int cnt = userService.deleteUserList(userList);

			if (cnt == userList.size()) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
