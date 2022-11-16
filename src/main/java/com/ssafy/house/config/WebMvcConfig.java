package com.ssafy.house.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.house.interceptor.SessionConfirmInterceptor;

@Configuration
@MapperScan(basePackages= {"com.ssafy.house.model.mapper"})

public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	SessionConfirmInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		.addPathPatterns("/board/**","/user/updateUser", "/user/deleteUser")
		.excludePathPatterns("/board/list");
	}	
}
