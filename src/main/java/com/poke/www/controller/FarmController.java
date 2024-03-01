package com.poke.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poke.www.service.FarmService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/farm")
@Controller
public class FarmController {
	
	private final FarmService farmService;
	
	@PostMapping("/{memberId}")
	public String test(@RequestParam("pokemonId")String pokemonId, @PathVariable("memberId") String memberId) {
		String slotArr[] = pokemonId.split(",");
		farmService.addPokemon(memberId, slotArr[0], slotArr[1], slotArr[2], slotArr[3], slotArr[4]);
		return "redirect:/member/{memberId}";
	}
}
