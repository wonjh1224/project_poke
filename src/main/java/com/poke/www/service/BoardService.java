package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.BoardVO;

public interface BoardService {

	List<BoardVO> getList();

	void register(BoardVO bvo);

	BoardVO getDetail(long bno);

}
