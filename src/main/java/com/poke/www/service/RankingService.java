package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PagingVO;

public interface RankingService {

	List<MemberVO> getMemberList(PagingVO pagingVO);

	int getMemberCount();

}
