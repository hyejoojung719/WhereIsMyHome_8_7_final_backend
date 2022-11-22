package com.ssafy.house.dto;

import lombok.Data;

@Data
// houseinfo 테이블이랑 housedeal 테이블 조인
public class Apart {
	private long aptCode;	// 아파트 코드	(houseinfo, housedeal)
	private int buildYear; 	// 설립년도(houseinfo)
	private String apartmentName; // 아파트 이름(houseinfo)
	private String dong;	// 동(houseinfo)
	private String floor;	// 층(housedeal)
	private String dealamount; // 거래금액(housedeal)
	private int dealYear; 	// 거래 년도(housedeal)
	private int dealMonth; 	// 거래 월(housedeal)
	private String area;	// 면적(housedeal)
	private String jibun; 	// 지번(houseinfo)
	private String lng;	// 경도(houseinfo)
	private String lat;	// 위도(houseinfo)
}
