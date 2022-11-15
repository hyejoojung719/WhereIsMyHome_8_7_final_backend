package com.ssafy.house.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		
		e.printStackTrace();
		
		String msg="처리 중 오류가 발생했습니다.";
		if(e instanceof SQLException) {
			msg="DB관련 오류 발생";
		}
		model.addAttribute("msg", msg);
		
		return "error/500";
	}
	
	//404 발생 시 처리 메소드
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException e) {

		e.printStackTrace();
		
		return "error/404";
	}
}