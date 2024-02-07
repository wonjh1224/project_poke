package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.BoardDTO;
import com.poke.www.domain.BoardVO;

public interface BoardService {

	List<BoardVO> getList();

	void register(BoardDTO boardDTO);

	BoardDTO getDetail(long bno);

	void delete(long bno);

	void modify(BoardDTO boardDTO);

	void deleteFile(String uuid);

}
