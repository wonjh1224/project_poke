package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class BoardVO {

	private long bno;
	private String title;
	private String writer;
	private String content;
	private String regAt;
	private String readCount;
	private String commentCount;
	
}
