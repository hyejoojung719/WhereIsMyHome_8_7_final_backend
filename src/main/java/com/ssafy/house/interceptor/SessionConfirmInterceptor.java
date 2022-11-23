package com.ssafy.house.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionConfirmInterceptor implements HandlerInterceptor{
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//OPTIONS 메소드로 넘어오는 preflight 요청은 true로 넘겨줌.
		if(HttpMethod.OPTIONS.matches(request.getMethod())) return true;
		
		
		//1. 세션에서 유저 정보가 있는지 체크 
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		//2-1. 유저 정보가 없는 경우 - 로그인 페이지로 보냄
		if(userId == null) {
//			response.sendRedirect(request.getContextPath());
			//컨트롤러로 요청가지 않도록 false 리턴
			return false;
		}
		//2-2. 유저 정보가 있는 경우 
		//요청을 컨트롤러로 정상적으로 보냄
		return true;
		
		/*
		log.debug("요청 메소드 종류 : {}", request.getMethod());
		
		//OPTIONS 메소드로 넘어오는 preflight 요청은 true로 넘겨줌.
		if(HttpMethod.OPTIONS.matches(request.getMethod())) return true;
		
		final String token = request.getHeader("access-token");
		
		//토큰 존재 여부 체크 
		if(token == null) {
			log.debug("Header에 Access-token 정보가 없음");
			response.getWriter().append("not Login");
			return false;
		}
		
		//토큰의 유효성 체크 
		try {
			Jwts.parser()
					.setSigningKey("ssafy".getBytes("UTF-8"))
					.parseClaimsJws(token);
			log.debug("토큰 존재하고 유효하므로 요청 정상 처리");
			return true;
		} catch (Exception e) {
			log.debug("token은 존재하나, 유효하지 않음 : {}", e.getMessage());
			response.getWriter().append("not Login");
			return false;
		}
		 */
	}
}
