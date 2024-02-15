package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.MemberVO;

@Mapper
public interface RankingMapper {

	List<MemberVO> selectMemberListByScore();

}
