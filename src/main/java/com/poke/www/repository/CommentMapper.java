package com.poke.www.repository;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.CommentVO;

@Mapper
public interface CommentMapper {

	int insert(CommentVO commentVO);

}
