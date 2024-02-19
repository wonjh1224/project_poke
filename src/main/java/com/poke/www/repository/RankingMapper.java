package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PagingVO;

@Mapper
public interface RankingMapper {

	List<MemberVO> selectMemberListByScore(PagingVO pagingVO);

	int selectMemberCount();

}
