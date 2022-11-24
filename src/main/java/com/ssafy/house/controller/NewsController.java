package com.ssafy.house.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.util.NaverNewsApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/news")
@Slf4j
public class NewsController {
	
	
	@GetMapping
    public  String getNews() throws Exception {
		  return NaverNewsApi.getNews();
    }

}
