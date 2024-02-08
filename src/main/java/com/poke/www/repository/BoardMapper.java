package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.BoardVO;
import com.poke.www.domain.PagingVO;

@Mapper
public interface BoardMapper {

	List<BoardVO> selectList(PagingVO pagingVO);

	void insert(BoardVO bvo);

	void delete(long bno);

	void update(BoardVO bvo);

	long maxBno();

	BoardVO selectOne(long bno);

	int getTotalCount(PagingVO pagingVO);

	


}
