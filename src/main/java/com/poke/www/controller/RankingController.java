package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PagingVO;
import com.poke.www.domain.RankingVO;
import com.poke.www.handler.PagingHandler;
import com.poke.www.service.RankingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/ranking")
public class RankingController {
	
	private final RankingService rankingService;
	
	@GetMapping
	public String ranking(Model m, PagingVO pagingVO) {
		
		List<MemberVO> list = rankingService.getMemberList(pagingVO);
		
		log.info("pagingVO {}", pagingVO);
		
		//전체 유저 수
		int totalMemberCount = rankingService.getMemberCount();
		
		PagingHandler ph = new PagingHandler(pagingVO, totalMemberCount);
		
		log.info("ph >>> {}", ph);
		
		
		//랭킹 테이블 만든 후 
		List<RankingVO> ranking = rankingService.getRankingList();
		log.info("ranking >>> {}", ranking);
		m.addAttribute("ranking", ranking);
		
		
		m.addAttribute("list", list);
		m.addAttribute("cnt", totalMemberCount);
		m.addAttribute("ph", ph);
		
		return "/ranking/ranking";
	}
	
	@PostMapping("/update")
	public String update() {
		List<MemberVO> list = rankingService.getMemberListOrderByScore();
		
		for(int i=0; i<list.size(); i++) {
			rankingService.updateRanking(list.get(i).getMemberId(), i+1);
		}
		
		return "1";
		
	}
	

	
	
	
	
}
