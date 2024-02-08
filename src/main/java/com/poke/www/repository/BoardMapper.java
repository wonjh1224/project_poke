package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.poke.www.domain.BoardVO;
import com.poke.www.domain.PagingVO;

@Mapper
public interface BoardMapper {

	List<BoardVO> selectList(@Param("pagingVO") PagingVO pagingVO, @Param("category")String category);

	void insert(BoardVO bvo);

	void delete(long bno);

	void update(BoardVO bvo);

	long maxBno();

	BoardVO selectOne(long bno);

	int getTotalCount(PagingVO pagingVO);

	List<BoardVO> selectListAllBoard(PagingVO pagingVO);

	List<BoardVO> selectListCateorized(@Param("pagingVO")PagingVO pagingVO, @Param("category")String category);

	


}
