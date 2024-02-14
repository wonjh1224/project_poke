package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.CommentVO;

public interface CommentService {

	int register(CommentVO commentVO);

	List<CommentVO> getList(long bno);

	int modify(CommentVO commentVO);

	int delete(long cno);

}
