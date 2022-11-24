package com.ssafy.house.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
		registry.addInterceptor(interceptor);
		//.addPathPatterns(/*"/board/**",*/"/user/updateUser", "/user/deleteUser")
		//.excludePathPatterns("**");
	}
	
	 // cors 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // API에 접근 가능한 URL
                .addMapping("/**").allowedOrigins("*")
                // 허용할 메소드 설정 
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
                        HttpMethod.PATCH.name())
                .allowedHeaders("*")
                // preflight 요청 결과의 캐시를 저장할 수 있는 시간
                .maxAge(1800);
        
    }
}
