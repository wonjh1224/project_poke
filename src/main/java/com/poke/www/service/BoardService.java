package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.BoardDTO;
import com.poke.www.domain.BoardVO;
import com.poke.www.domain.PagingVO;

public interface BoardService {

	void register(BoardDTO boardDTO);

	BoardDTO getDetail(long bno);

	void delete(long bno);

	void modify(BoardDTO boardDTO);

	void deleteFile(String uuid);

	int getTotalCountCategoized(String category);

	List<BoardVO> getListAllBoard(PagingVO pagingVO);

	List<BoardVO> getListCategorized(PagingVO pagingVO, String category);

	int getTotalCount();

}
