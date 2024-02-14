package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poke.www.domain.MemberVO;
import com.poke.www.service.MemberService;
import com.poke.www.service.RankingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ranking")
public class RankingController {
	private final RankingService rankingService;
	private final MemberService memberService;
	
	
	@GetMapping
	public String getRankingPage(Model m) {
		List<MemberVO> memberList = memberService.getRanking();
		m.addAttribute("memberList",memberList);
		return "/ranking/ranking";
	}
	
}
