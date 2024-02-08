package com.poke.www.handler;


import com.poke.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PagingHandler {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	private PagingVO pagingVO;
	
	private int totalCount;
	
	public PagingHandler(PagingVO pagingVO, int totalCount) {
		this.pagingVO = pagingVO;
		this.totalCount = totalCount;
		
		endPage = (int) (Math.ceil (pagingVO.getPageNo() / (double) pagingVO.getQty()) ) * pagingVO.getQty();
		startPage = endPage - 9;
		
		//스타트페이지 수정
		if(startPage < 0) {
			startPage = 1;
		}
		
		int realEndPage = (int) Math.ceil( totalCount / (double)pagingVO.getQty() );
		
		if(endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		prev = startPage > 1;
		next = endPage < realEndPage;
		
	}

}

	