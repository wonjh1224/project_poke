package com.poke.www.service;

import org.springframework.stereotype.Service;

import com.poke.www.domain.MemberVO;
import com.poke.www.repository.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper mapper;

	@Override
	public int register(MemberVO mvo) {
		return mapper.insertMember(mvo);
	}

	@Override
	public MemberVO login(String loginId, String password) {
		MemberVO mvo = mapper.selectMemberById(loginId);
		if(mvo != null && mvo.getPassword().equals(password)) {
			return mvo;
		}
		return null;			
		
	}

	@Override
	public MemberVO getMember(String memberId) {
		return mapper.selectMemberById(memberId);
	}
	
	
	@Override
	public int modifyPointByMemberId(String memberId, int price) {
		return mapper.updatePointById(price,memberId);
	}


}
