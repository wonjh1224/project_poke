package com.poke.www.repository;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.MemberVO;

@Mapper
public interface MemberMapper {

	int insertMember(MemberVO mvo);

	MemberVO selectMemberById(String loginId);

	int updatePointById(int price, String memberId);

}
