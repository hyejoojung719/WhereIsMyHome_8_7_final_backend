package com.ssafy.house.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.house.dto.Board;
import com.ssafy.house.model.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/board")
@Slf4j
public class BoardController {

	@Autowired
	BoardService boardService;

	@Autowired
	ServletContext servletContext;


	// **************************** Fetch Method ******************************
	// 글 목록 불러오기
	@GetMapping
	public ResponseEntity<?> listArticle(
			@RequestParam("key") String key, @RequestParam("word") String word) throws Exception{
		log.debug("listArticle() 메소드 실행 ");

		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key", key);
			map.put("word", word);
			List<Board> list = boardService.listArticle(map);
			
			log.debug("key는 {}", key);
			log.debug("word는 {}", word);
			log.debug("list는 {}", list);
			
			
			log.debug("목록 데이터 :{}",list.toString());
			if(list != null && !list.isEmpty()) {
				//	목록을 얻어왔거나, 얻어왔는데 비어있지 않다면
				return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
				// list가 OK와 함께 넘어감
			}else {
				// 값이 없다.
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				// 값이 없으니 Void 타입(객체니까V)
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 서버쪽에서 에러가 났다
			return exceptionHandling(e);
		}
	}
	
	// 글 작성하기
	@PostMapping
	public void writeArticle(HttpSession session, @RequestBody Board board) throws Exception{

		log.debug("writeArticle() 메소드 실행 ");

		String userId = (String) session.getAttribute("userId");
		board.setUserId(userId);

		int cnt = boardService.writeArticle(board);
	}
	
	// 글 수정하기
	@PutMapping
	public void modifyArticle(@RequestBody Board board) throws Exception{

		log.debug("modifyArticle() 메소드 실행 ");

		boardService.modifyArticle(board);
	}
	
	// 글 삭제하기
	@DeleteMapping("{articleNo}")
	public void deleteArticle(@PathVariable("articleNo") String articleNo) throws Exception{

		log.debug("deleteArticle() 메소드 실행 ");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("articleNo", articleNo);

		boardService.deleteArticle(map);

	}
	
	
	// 글 상세 보기
	@GetMapping("{articleNo}")
	public ResponseEntity<?> getArticle(@PathVariable("articleNo") String articleNo) throws Exception{

		log.debug("getArticle() 메소드 실행 ");

		
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("articleNo", Integer.parseInt(articleNo));

			// 조회수
			boardService.updateHit(map);

			//DB에서 글 가져오기
			Board board = boardService.getArticle(map);
			
			if(board != null) {
				//	목록을 얻어왔거나, 얻어왔는데 비어있지 않다면
				return new ResponseEntity<Board>(board, HttpStatus.OK);
				// list가 OK와 함께 넘어감
			}else {
				// 값이 없다.
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				// 값이 없으니 Void 타입(객체니까V)
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 서버쪽에서 에러가 났다
			return exceptionHandling(e);
		}
	}
	

	// alt + shift + m 눌러서 중복된 코드를 대체 가능
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
