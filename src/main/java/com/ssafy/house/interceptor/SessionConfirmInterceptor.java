package com.ssafy.house.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SessionConfirmInterceptor implements HandlerInterceptor{
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.debug("prehandle 들어옴");
		
		log.debug("요청 메소드 종류 : {}", request.getMethod());
		return true;
		
//		final String token = request.getHeader("access-token");
//		
//		//토큰 존재 여부 체크 
//		if(token == null) {
//			log.debug("Header에 Access-token 정보가 없음");
//			response.getWriter().append("not Login");
//			return false;
//		}
//		
//		//토큰의 유효성 체크 
//		try {
//			Jwts.parser()
//					.setSigningKey("CARR0TCHEE2ECAK3@@E".getBytes("UTF-8"))
//					.parseClaimsJws(token);
//			log.debug("토큰 존재하고 유효하므로 요청 정상 처리");
//			return true;
//		} catch (Exception e) {
//			log.debug("token은 존재하나, 유효하지 않음 : {}", e.getMessage());
//			response.getWriter().append("not Login");
//			return false;
//		}
		 
	}
}
