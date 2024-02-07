package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poke.www.domain.PokemonVO;
import com.poke.www.service.PokemonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	private final PokemonService pokemonService;
	
	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("msg","메세지테스트");
		return "index";
	}
	
	@ResponseBody
	@PutMapping("/pokemon-init")
	public String pokemonInit(@RequestBody List<PokemonVO> list) {
		pokemonService.cleanTable();
		for(PokemonVO pvo : list) {
			pokemonService.addPokemon(pvo);
		}
		
		
		return String.valueOf(list.size());
	}
}
