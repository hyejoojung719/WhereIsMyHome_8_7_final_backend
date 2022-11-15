package com.ssafy.house.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.house.dto.Board;

public interface BoardService {
	
	int writeArticle(Board board) throws Exception;
	List<Board> listArticle(Map<String, Object> map) throws Exception;
	Board getArticle(Map<String, Object> map) throws Exception;
	void updateHit(Map<String, Object> map) throws Exception;
	void modifyArticle(Board board) throws Exception;
	void deleteArticle(Map<String, Object> map) throws Exception;

}
