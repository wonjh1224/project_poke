package com.poke.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poke.www.service.FarmService;
import com.poke.www.service.MemberService;
import com.poke.www.service.PokemonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/farm")
@Controller
public class FarmController {
	
	private final FarmService farmService;
	private final PokemonService pokemonService;
	private final MemberService memberService;
	
	@PostMapping("/add/{memberId}")
	public String add(@RequestParam("pokemonId")String pokemonId, @PathVariable("memberId") String memberId) {
		String slotArr[] = pokemonId.split(",");
		
		//03.07 등록한 포켓몬 총합 점수 칼럼 추가 total-point 
		int totalPoint = pokemonService.getTotalPoint(slotArr[0], slotArr[1], slotArr[2], slotArr[3], slotArr[4]);
		log.info("totalPoint >>> {}", totalPoint);
		
		farmService.addPokemon(memberId, slotArr[0], slotArr[1], slotArr[2], slotArr[3], slotArr[4], totalPoint);
		return "redirect:/member/{memberId}";
	}
	
	@PostMapping("/del/{memberId}")
	public String del(@PathVariable("memberId")String memberId) {
		log.info("memberId >>>>> {}", memberId);
		
		int point = farmService.getTotalPoint(memberId);
		point = point / 10;
		log.info("score >>> {}", point);
		
		//멤버 농장 완료 보상(score) 업데이트 
		memberService.updatePoint(memberId, point);
		
		//농장 삭제
		farmService.delPokemon(memberId);
		
		return "redirect:/member/{memberId}";
	}
}
