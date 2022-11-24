package com.ssafy.house.dto;

import lombok.Data;

@Data
public class News {
	private Item[] items;

	@Data
	static class Item {
		public String title;
		public String link;
		public String image;
		public String pubDate;
		public String description;
	}
}
