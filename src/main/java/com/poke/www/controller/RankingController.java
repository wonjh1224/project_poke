package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		List<RankingVO> ranking = rankingService.getRankingList(pagingVO);
		
		int totalRankingCount = rankingService.getTotalCount();
		
		PagingHandler ph = new PagingHandler(pagingVO, totalRankingCount);
		
		log.info("ph {}", ph);
		log.info("pagingVO {}", pagingVO);
		log.info("ranking >>> {}", ranking);
		
		m.addAttribute("cnt", totalRankingCount);
		m.addAttribute("ph", ph);
		m.addAttribute("ranking", ranking);

		return "/ranking/ranking";
	}
	
	@ResponseBody
	@Transactional
	@PostMapping("/update")
	public String update() {
		List<MemberVO> list = rankingService.getMemberListOrderByScore();
		
		rankingService.updateScore();
		
		for(int i=0; i<list.size(); i++) {
			rankingService.updateRanking(list.get(i).getMemberId(), i+1);
		}
		
		return "1";
		
	}
	

	
	
	
	
}
