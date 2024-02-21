package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PagingVO;
import com.poke.www.domain.RankingVO;

@Mapper
public interface RankingMapper {

	List<MemberVO> selectMemberListOrderByScore();

	void updateRanking(String memberId, int i);
	

	List<RankingVO> selectRankingList(PagingVO pagingVO);

	void insert(String memberId);

	int selectTotalCount();

	void updateScore();

}
