package com.poke.www.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import com.poke.www.domain.BoardDTO;
import com.poke.www.domain.BoardVO;
import com.poke.www.domain.FileVO;
import com.poke.www.service.BoardService;


@Component
public class BoardServiceTest {


	BoardService boardService;
	
	@Test
	void insert() {
	
		BoardVO boardVo = new BoardVO();
		List<FileVO>flist = null;
		for(int i=0; i<100; i++) {
			boardVo.setTitle("제목 "+i);
			boardVo.setWriter("작성자 "+i);
			boardVo.setContent("내용 "+i);
			
			boardService.register(new BoardDTO(boardVo, flist));
		}
	}
	
}
