package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class FileVO {

	private String uuid;
	private String saveDir;
	private String fileName;
	private long fileSize;
	private long bno;
	private String regAt;
	
}
