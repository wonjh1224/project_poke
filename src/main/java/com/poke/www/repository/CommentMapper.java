package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.CommentVO;

@Mapper
public interface CommentMapper {

	int insert(CommentVO commentVO);

	List<CommentVO> selectList(long bno);

	int update(CommentVO commentVO);

	int delete(long cno);

}
