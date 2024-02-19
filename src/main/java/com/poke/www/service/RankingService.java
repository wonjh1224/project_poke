package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PagingVO;
import com.poke.www.domain.RankingVO;

public interface RankingService {

	List<MemberVO> getMemberList(PagingVO pagingVO);

	int getMemberCount();

	void register(String memberId);

	List<MemberVO> getMemberListOrderByScore();

	void updateRanking(String memberId, int i);

	List<RankingVO> getRankingList();

}
