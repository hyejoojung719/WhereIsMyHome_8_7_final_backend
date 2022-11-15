package com.ssafy.house.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.house.dto.User;
import com.ssafy.house.model.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	// **************************** Fetch Method ******************************
	// 로그인 => 회원인지 체크 후 세션 처리
	@ResponseBody
	@GetMapping
	public ResponseEntity<?> signIn(Model model, HttpSession session, User user) throws SQLException {
		log.debug("signIn() 메소드 요청");
		log.debug("id : {} pwd : {}", user.getUser_id(), user.getUser_password());

		boolean check = userService.signIn(user);

		if (check) {
			session.setAttribute("userId", user.getUser_id());
			return new ResponseEntity<Void>(HttpStatus.OK);
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
	@PostMapping(value = "/signUp")
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
	@RequestMapping(value = "/idCheck")
	@ResponseBody
	public ResponseEntity<?> idCheck(Model model, @RequestParam String user_id) throws SQLException {
		log.debug("idCheck() 메소드 요청");
		log.debug("user_id : {}", user_id);
		int cnt = userService.idCheck(user_id);

		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}

	// 로그아웃 -> 로그아웃 -> 세션 삭제 및 메인페이지 이동
	@ResponseBody
	@GetMapping(value = "/signOut")
	public ResponseEntity<?> signOut(HttpSession session) {
		log.debug("signOut() 메소드 요청");
		session.removeAttribute("userId");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 마이페이지 이동 => 회원 정보 가져오기
	@ResponseBody
	@RequestMapping(value = "/myPage")
	public ResponseEntity<?> myPage(Model model, HttpSession session) throws SQLException {
		log.debug("myPage() 메소드 요청");
		String id = (String) session.getAttribute("userId");

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