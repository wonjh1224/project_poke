package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PagingVO;
import com.poke.www.repository.RankingMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RankingServiceImpl implements RankingService{

	private final RankingMapper rankingMapper;

	@Override
	public List<MemberVO> getMemberList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return rankingMapper.selectMemberListByScore(pagingVO);
	}

	@Override
	public int getMemberCount() {
		// TODO Auto-generated method stub
		return rankingMapper.selectMemberCount();
	}

	@Override
	public void register(String memberId) {
		rankingMapper.insert(memberId);
		
	}

	@Override
	public List<MemberVO> getMemberListOrderByScore() {
		// TODO Auto-generated method stub
		return rankingMapper.selectMemberListOrderByScore();
	}

	@Override
	public void updateRanking(String memberId, int i) {
		rankingMapper.updateRanking(memberId, i);
		
	}
	
}
