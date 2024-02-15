package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor
public class PagingVO {

	private int pageNo;
	private int qty;
	
	private String type;
	private String keyword;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public int getPageStart() {
		return (pageNo - 1)*qty;
	}
	
	public String[] getTypeToArray() {
		return this.type == null ? new String [] {} : this.type.split("");
	}
	
}
