package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.MemberVO;
import com.poke.www.repository.RankingMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RankingServiceImpl implements RankingService{

	private final RankingMapper rankingMapper;

	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		return rankingMapper.selectMemberListByScore();
	}
	
}
