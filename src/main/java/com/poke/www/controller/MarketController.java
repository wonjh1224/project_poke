package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.poke.www.domain.MarketItemVO;
import com.poke.www.domain.MemberVO;
import com.poke.www.domain.PokemonStorageVO;
import com.poke.www.service.MarketService;
import com.poke.www.service.StorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {
	private final MarketService marketService;
	private final StorageService storageService;
	
	@GetMapping
	public String getMarketMain(Model m) {
		return "/market/market";
	}
	
	@GetMapping("/register")
	public String getItemRegsiterPage(Model m,
			@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember) {
		if(loginMember == null) {
			return "redirect:/login";
		}
		List<PokemonStorageVO> list = storageService.getPokemonsByMemberId(loginMember.getMemberId());
		m.addAttribute("list",list);
		return "/market/register";
	}
	@PostMapping	
	public String registerItem(MarketItemVO marketItemVO,@RequestParam("storageId")int storageId) {
		log.info("mvo : {}",marketItemVO);
		marketService.addItem(marketItemVO);
		storageService.removePokemonByStorageId(storageId);
		return "redirect:/market";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public List<MarketItemVO> getItemList(){
		List<MarketItemVO> list = marketService.getItems();
		return list;
	}
	
	@ResponseBody
	@PostMapping("/purchase")
	public String buyItem(@RequestBody MarketItemVO bodyMarketItemVO) {
		log.info("마켓@@@@@@@ {}",bodyMarketItemVO);
		MarketItemVO marketItemVO = marketService.getItemByItemId(bodyMarketItemVO.getItemId());
		
		return "ok";
	}
}
