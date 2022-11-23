package com.ssafy.house.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.dto.Board;
import com.ssafy.house.model.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/board")
@Slf4j
public class BoardController {

	@Autowired
	BoardService boardService;

	@Autowired
	ServletContext servletContext;


	// **************************** Fetch Method ******************************
	// 글 목록 불러오기
	@GetMapping("/list")
	public ResponseEntity<?> listArticle() throws Exception{
		log.debug("listArticle() 메소드 실행 ");

		try {
			List<Board> list = boardService.listArticle();
			
			log.debug("list는 {}", list);
			
			
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
	public void writeArticle(@RequestBody Board board, @RequestHeader(value="access-token") String token) throws Exception{

		log.debug("writeArticle() 메소드 실행 ");

		String decodedToken = new String(Base64.getDecoder().decode(token.split("\\.")[1]));

		//JSONParser 생성
		JSONParser jsonParser = new JSONParser();
		//JSONParser를 통해 JSONObject 생성
		JSONObject jsonObject = (JSONObject) jsonParser.parse(decodedToken);
		//userId 값 추출
		String user_id = (String) jsonObject.get("userId");
		board.setUserId(user_id);

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
			
			log.debug("board 정보 {}",board);
			
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
