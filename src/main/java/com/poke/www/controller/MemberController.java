package com.poke.www.controller;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;


import com.poke.www.domain.FarmVO;
import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PokedexVO;
import com.poke.www.domain.PokemonVO;
import com.poke.www.service.FarmService;
import com.poke.www.service.MemberService;
import com.poke.www.service.PokedexService;
import com.poke.www.service.PokemonService;
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
	private final PokemonService pokemonService;
	
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
		
		//서버 시간(현재)
		LocalDateTime now = LocalDateTime.now();
		String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime nowDate = LocalDateTime.parse(time, formatter);
		m.addAttribute("now", nowDate);
		
		//랭킹
		int ranking = rankingService.getRankingByMemberId(memberId);
		m.addAttribute("ranking", ranking);
		
		int score = rankingService.getScoreByMemberId(memberId);
		//도감 등록 퍼센트
		double percent = Math.round((score / 204451.0) * 100 * 100) / 100.0;
		m.addAttribute("per", percent);
		
		//등록된 도감 포켓몬 목록
		List<PokedexVO> pokedexList = pokedexService.getPokemonsByMemberId(memberId);
		log.info("pokedex List {}", pokedexList);
		
		List <PokemonVO> list = new ArrayList<>();
		for(int i=0; i<pokedexList.size(); i++) {
			PokemonVO pvo = pokemonService.getPokemonByPokemonId(pokedexList.get(i).getPokemonId());
			list.add(pvo);
		}
		m.addAttribute("list", list);
		
		
		FarmVO farm = farmService.getFarmList(memberId);

		log.info("id {}", memberId);
		log.info("farmVO {}", farm);
		
		m.addAttribute("farm", farm);
		
		if(farm != null) {
			//농장에 등록된 포켓몬 이미지 불러오기
			String pokemonImage [] = pokemonService.getPokemonImage(farm.getSlot1(), farm.getSlot2(), farm.getSlot3(), farm.getSlot4(), farm.getSlot5());
			m.addAttribute("image", pokemonImage);			
			
			//농장 등록 후 끝나는 시간
			String end = farmService.getEndDate(memberId);
			LocalDateTime endDate = LocalDateTime.parse(end, formatter);
			
			//타이머(보상 시간)
			Duration rnTime = Duration.between(nowDate, endDate);
		
			log.info("rnTime {}", rnTime.toSeconds());
			m.addAttribute("rnTime", rnTime.toSeconds());
					
		}
		
		return "/member/detail";
	}
	

	
}
