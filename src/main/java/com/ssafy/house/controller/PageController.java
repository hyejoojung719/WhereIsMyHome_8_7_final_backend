package com.ssafy.house.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.house.dto.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/go")
@Slf4j
public class PageController {
    // UserController
    // 비밀번호 찾기 페이지 이동
    @GetMapping(value = "/findPwd")
    public String goFindPwd(Model model) {
        log.debug("goFindPwd() 메소드 요청");
        return "user/findpwd";
    }
    
    //비밀번호 찾기 결과 페이지 이동
    @GetMapping(value="/findPwdResult")
    public String goFindPwdResult(Model model) {
        log.debug("goFindPwdResult() 메소드 요청");
        return "user/findpwdresult";
    }

    // 회원 가입페이지 이동
    @GetMapping(value = "/signUp")
    public String goSignUp(Model model) {
        log.debug("goSignUp() 메소드 요청");
        return "user/signup";
    }

    // 로그아웃 -> 로그아웃 -> 세션 삭제 및 메인페이지 이동
    @GetMapping(value = "/signOut")
    public String signOut(HttpSession session) {
        log.debug("signOut() 메소드 요청");
        session.removeAttribute("userId");
        return "redirect:/";
    }

    // 마이페이지 이동 => 회원 정보 가져오기
    @GetMapping(value = "/myPage")
    public String goMyPage(Model model, HttpSession session) throws SQLException {
        log.debug("goMyPage() 메소드 요청");
        return "user/mypage";
    }

    // BoardController
    // 글 목록으로 가기
    @GetMapping("/boardlist")
    public String goBoardList(@RequestParam("key") String key, @RequestParam("word") String word, Model model) {
        log.debug("goBoardList() 메소드 실행 ");
        model.addAttribute("key", key);
        model.addAttribute("word", word);
        
        log.debug("key 값 {}", key);
        log.debug("word 값  {}", word);
        
        return "board/list";
    }

    // 글 상세페이지로 가기
    @GetMapping("/boarddetail")
    public String goBoardDetail(@RequestParam("articleNo") int articleNo, Model model) {
        log.debug("goBoardDetail() 메소드 실행");
        model.addAttribute("articleNo", articleNo);
        return "board/detail";
    }
    
    
    // 글 쓰기 페이지 이동 
    @GetMapping("/boardwrite")
    public String goBoardWrite() {
        log.debug("goBoardWrite() 메소드 실행 ");
        return "board/write";
    }
    
    // 글 수정페이지로 가기
    @GetMapping("/boardmodify")
    public String goBoardModify(@RequestParam("articleNo") int articleNo, Model model) throws Exception {
        log.debug("goBoardModify() 메소드 실행 ");
        model.addAttribute("articleNo", articleNo);
        return "board/modify";
    }

    // ApartController
    // 카카오맵 페이지 이동
 	@GetMapping("/map")
 	public String goMap() {
 		log.debug("goMap() 메소드 실행 ");
 		return "map/kakaomap";
 	}
 	
    // 관심 아파트 목록으로 가기
    @GetMapping("/interestapart")
    public String goInterestApart() throws Exception{
    	log.debug("goInterestApart() 메소드 실행");
    	return "map/interest";
    }
}