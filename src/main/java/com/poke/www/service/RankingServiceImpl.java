package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PagingVO;
import com.poke.www.domain.RankingVO;
import com.poke.www.repository.RankingMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RankingServiceImpl implements RankingService{

	private final RankingMapper rankingMapper;

	@Override
	public List<MemberVO> getMemberListOrderByScore() {
		// TODO Auto-generated method stub
		return rankingMapper.selectMemberListOrderByScore();
	}

	@Override
	public void updateRanking(String memberId, int i) {
		rankingMapper.updateRanking(memberId, i);
	}

	@Override
	public List<RankingVO> getRankingList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return rankingMapper.selectRankingList(pagingVO);
	}

	@Override
	public void register(String memberId) {
		rankingMapper.insert(memberId);
		
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return rankingMapper.selectTotalCount();
	}

	@Override
	public void updateScore() {
		rankingMapper.updateScore();
		
	}

	@Override
	public Integer getRankingByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return rankingMapper.selectRankingByMemberId(memberId);
	}

	@Override
	public int getScoreByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return rankingMapper.selectScoreByMemberId(memberId);
	}
	
}
