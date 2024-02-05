package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.BoardVO;

@Mapper
public interface BoardMapper {

	List<BoardVO> selectList();

	void insert(BoardVO bvo);

	BoardVO selectOne(long bno);

	void delete(long bno);

	void update(BoardVO bvo);

}
