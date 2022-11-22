package com.ssafy.house.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.dto.Board;
import com.ssafy.house.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public int writeArticle(Board board) throws Exception {
		return boardMapper.writeArticle(board);
	}

	@Override
	public List<Board> listArticle() throws Exception {
		return boardMapper.listArticle();
	}

	@Override
	public Board getArticle(Map<String, Object> map) throws Exception {
		return boardMapper.getArticle(map);
	}

	@Override
	public void updateHit(Map<String, Object> map) throws Exception {
		boardMapper.updateHit(map);
	}

	@Override
	public void modifyArticle(Board board) throws Exception {
		boardMapper.modifyArticle(board);
	}

	@Override
	public void deleteArticle(Map<String, Object> map) throws Exception {
		boardMapper.deleteArticle(map);
	}

}
