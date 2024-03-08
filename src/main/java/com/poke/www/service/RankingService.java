package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PagingVO;
import com.poke.www.domain.RankingVO;

public interface RankingService {

	List<MemberVO> getMemberListOrderByScore();

	void updateRanking(String memberId, int i);

	List<RankingVO> getRankingList(PagingVO pagingVO);

	void register(String memberId);

	int getTotalCount();

	void updateScore();

	int getRankingByMemberId(String memberId);

	int getScoreByMemberId(String memberId);

}
