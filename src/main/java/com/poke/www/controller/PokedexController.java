package com.poke.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poke.www.service.PokedexService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pokedex")
public class PokedexController {
	private final PokedexService pokedexService;
	
	@GetMapping
	public String getPokedexPage() {
		return "/pokedex/pokedex";
	}
}
