package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poke.www.domain.BoardDTO;
import com.poke.www.domain.BoardVO;
import com.poke.www.domain.FileVO;
import com.poke.www.domain.PagingVO;
import com.poke.www.handler.FileHandler;
import com.poke.www.handler.PagingHandler;
import com.poke.www.service.BoardService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
@Slf4j
public class BoardController {

	private final BoardService boardService;
	
	private final FileHandler fh;
	
	@GetMapping("/test")
	public String testRegister() {
		List<FileVO> flist = null;
		for(int i=0; i<100; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("제목"+i);
			bvo.setWriter("작성"+i);
			bvo.setContent("내용"+i);
			bvo.setCategory("정보");
			boardService.register(new BoardDTO(bvo, flist));
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>> bvo >>> {}", bvo);
		log.info("category {}", bvo.getCategory());
		List<FileVO> flist = null;
		
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		if(bvo.getTitle() == null || bvo.getTitle() == "" || bvo.getWriter() == null || bvo.getWriter() == "" ||
				bvo.getContent() == null || bvo.getContent() == "") {
			log.info(">>> register bvo >>> {}", bvo);
			return "redirect:/board/list";
		}else {			
			boardService.register(new BoardDTO(bvo, flist));
			return "redirect:/board/list";
		}
		
	}
	
	
	@GetMapping({"/list"})
	public String list(Model m, PagingVO pagingVO, @RequestParam(name="category", required=false) String category) {
		log.info("pagingVO : {}", pagingVO);
		log.info("category : {}", category);
			
		int totalCount = 0;
		if(category == null) {
			totalCount = boardService.getTotalCount(pagingVO);
		}else {
			totalCount = boardService.getTotalCountCategoized(category);			
		}
		
		if(totalCount < pagingVO.getQty()) {
			pagingVO.setQty(totalCount);
		}
		
		log.info("qty : {}", pagingVO.getQty());
		log.info("totalCount : {}", totalCount);
		
		PagingHandler ph = new PagingHandler(pagingVO, totalCount);
	
		List<BoardVO> list;
		
		if(category == null) {
			list = boardService.getListAllBoard(pagingVO);
     	}
		else {
			list = boardService.getListCategorized(pagingVO, category);
		}
	
		m.addAttribute("category", category);
		m.addAttribute("list", list);	
		m.addAttribute("ph", ph);
		return "/board/list";
		
	}
	
	@GetMapping("/detail/{bno}")
	public String detail(@PathVariable("bno") long bno, Model m) {
		
		BoardDTO bdto = boardService.getDetail(bno);
	
		m.addAttribute("bdto", bdto);
		
		return "/board/detail";	
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("bno") long bno) {
		boardService.delete(bno);
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files
			,@RequestParam("uuids")String uuids) {
		
		String [] uuidsArr = uuids.split(",");
		for(String uuid : uuidsArr) {
			log.info(uuid);
			boardService.deleteFile(uuid);
		}
		
		List<FileVO> flist = null;
		
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		boardService.modify(new BoardDTO(bvo, flist));
		
		return "redirect:/board/list";
	}
	
	
}
