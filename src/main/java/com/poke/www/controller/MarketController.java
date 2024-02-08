package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poke.www.domain.MarketItemVO;
import com.poke.www.service.MarketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {
	private final MarketService marketService;
	
	@GetMapping
	public String getMarketMain(Model m) {
		List<MarketItemVO> list = marketService.getItems();
		m.addAttribute("list",list);
		return "/market/market";
	}
}
