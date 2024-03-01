package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poke.www.domain.FarmVO;
import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PokedexVO;
import com.poke.www.domain.PokemonVO;
import com.poke.www.service.FarmService;
import com.poke.www.service.MemberService;
import com.poke.www.service.PokedexService;
import com.poke.www.service.RankingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final RankingService rankingService;
	private final PokedexService pokedexService;
	private final FarmService farmService;
	
	@GetMapping("/register/test")
	public void registerTest() {
		for(int i=223; i<250; i++) {
			MemberVO mvo = new MemberVO();
			mvo.setMemberId("Test"+i);
			mvo.setPassword("test"+i);
			mvo.setName("Tester"+" ["+i+"]");
			mvo.setBirth("2024-02-21");
			mvo.setEmail("test@"+i);
			mvo.setPhone("010-"+i);
			int isOk = memberService.register(mvo);
			rankingService.register(mvo.getMemberId());
		}
	}
	
	@GetMapping("/register")
	public String getRegisterForm() {
		return "/member/register";
	}
	@PostMapping("/register")
	public String memberRegister(MemberVO mvo) {
		int isOk = memberService.register(mvo);
		
		//랭킹 db에도 추가
		rankingService.register(mvo.getMemberId());
		
		return "redirect:/";
	}
	@GetMapping("/login")
	public String getLoginForm() {
		
		return "/member/login";
	}
	@PostMapping("/login")
	public String memberLogin(MemberVO mvo, HttpServletRequest request) {
		MemberVO loginMember = memberService.login(mvo.getMemberId(),mvo.getPassword());
		if(loginMember == null) {
			return "/member/login";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("loginMemberId", loginMember.getMemberId());
		
		return "redirect:/";
	}
	@PostMapping("/logout")
	public String memberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/member")
	public MemberVO getMember(@RequestBody MemberVO mvo) {
		return memberService.getMember(mvo.getMemberId());
	}
	
	
	@GetMapping("/member/{memberId}")
	public String detailPage(@PathVariable("memberId")String memberId, Model m) {
		int ranking = rankingService.getRankingByMemberId(memberId);
		m.addAttribute("ranking", ranking);
		
		int score = rankingService.getScoreByMemberId(memberId);
		double percent = Math.round((score / 204451.0) * 100 * 100) / 100.0;
		m.addAttribute("per", percent);
		
		List<PokedexVO> list = pokedexService.getPokemonsByMemberId(memberId);
		m.addAttribute("list", list);
		
		FarmVO farm = farmService.getFarmList(memberId);

		log.info("id {}", memberId);
		log.info("farmVO {}", farm);
		m.addAttribute("farm", farm);

		
		return "/member/detail";
	}
	
	
	
}
