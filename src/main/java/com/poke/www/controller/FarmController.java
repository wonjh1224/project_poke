package com.poke.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poke.www.service.FarmService;
import com.poke.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/farm")
@Controller
public class FarmController {
	
	private final FarmService farmService;
	private final MemberService memberService;
	
	@PostMapping("/add/{memberId}")
	public String add(@RequestParam("pokemonId")String pokemonId, @PathVariable("memberId") String memberId) {
		String slotArr[] = pokemonId.split(",");
		farmService.addPokemon(memberId, slotArr[0], slotArr[1], slotArr[2], slotArr[3], slotArr[4]);
		return "redirect:/member/{memberId}";
	}
	
	@PostMapping("/del/{memberId}")
	public String del(@PathVariable("memberId")String memberId) {
		log.info("memberId >>>>> {}", memberId);
		farmService.delPokemon(memberId);
		
		return "redirect:/member/{memberId}";
	}
}
