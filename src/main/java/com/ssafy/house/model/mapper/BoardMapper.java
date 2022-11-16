package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.house.dto.Board;


public interface BoardMapper {

	int writeArticle(Board board) throws SQLException;
	List<Board> listArticle(Map<String, Object> map) throws SQLException;
	Board getArticle(Map<String, Object> map) throws SQLException;
	void updateHit(Map<String, Object> map) throws SQLException;
	void modifyArticle(Board board) throws SQLException;
	void deleteArticle(Map<String, Object> map) throws SQLException;
	
}
