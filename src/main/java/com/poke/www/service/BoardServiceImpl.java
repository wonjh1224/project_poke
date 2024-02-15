package com.poke.www.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poke.www.domain.BoardDTO;
import com.poke.www.domain.BoardVO;
import com.poke.www.domain.FileVO;
import com.poke.www.domain.PagingVO;
import com.poke.www.repository.BoardMapper;
import com.poke.www.repository.FileMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	private final FileMapper fileMapper;

	@Transactional
	@Override
	public void register(BoardDTO boardDTO) {

		if (boardDTO.getFlist() == null) {
			boardMapper.insert(boardDTO.getBoardVO());
		} else {
			boardMapper.insert(boardDTO.getBoardVO());
			long bno = boardMapper.maxBno();
			for (FileVO fvo : boardDTO.getFlist()) {
				fvo.setBno(bno);
				fileMapper.insertFile(fvo);
			}
		}

	}

	@Override
	public BoardDTO getDetail(long bno) {
		BoardVO bvo = boardMapper.selectOne(bno);
		List<FileVO> flist = fileMapper.selectFile(bno);
		BoardDTO boardDTO = new BoardDTO(bvo, flist);
		return boardDTO;
	}

	@Override
	public void delete(long bno) {
		boardMapper.delete(bno);
		fileMapper.deleteFile(bno);

	}

	@Transactional
	@Override
	public void modify(BoardDTO boardDTO) {

		if (boardDTO.getFlist() == null) {
			boardMapper.update(boardDTO.getBoardVO());
		} else {
			boardMapper.update(boardDTO.getBoardVO());
			long bno = boardMapper.maxBno();
			for (FileVO fvo : boardDTO.getFlist()) {
				fvo.setBno(bno);
				fileMapper.insertFile(fvo);
			}
		}

	}

	@Override
	public void deleteFile(String uuid) {
		fileMapper.deleteFileUUID(uuid);
		
	}

	@Override
	public int getTotalCountCategoized(String category) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCountCategoized(category);
	}

	@Override
	public List<BoardVO> getListAllBoard(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return boardMapper.selectListAllBoard(pagingVO);
	}

	@Override
	public List<BoardVO> getListCategorized(PagingVO pagingVO, String category) {
		// TODO Auto-generated method stub
		return boardMapper.selectListCateorized(pagingVO, category);
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount(pagingVO);
	}

}
