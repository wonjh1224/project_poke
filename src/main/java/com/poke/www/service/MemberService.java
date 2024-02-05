package com.poke.www.service;

import com.poke.www.domain.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	MemberVO login(String memberId, String password);

}
