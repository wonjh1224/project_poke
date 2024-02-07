package com.poke.www.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poke.www.domain.ItemStorageVO;
import com.poke.www.domain.MemberVO;
import com.poke.www.domain.ProductVO;
import com.poke.www.service.MemberService;
import com.poke.www.service.StorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {
	private final StorageService storageService;
	private final MemberService memberService;
	
	@GetMapping({"/{memberId}/item","/{memberId}"})
	public String getStorage(Model m,
//			@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
			@PathVariable("memberId") String memberId) {
		MemberVO mvo = memberService.getMember(memberId);
		if(mvo==null) {
			return "error";
		}
		List<ItemStorageVO> itemList =  storageService.getItemsByMemberId(memberId);
		log.info("list ::::::::::: {}",itemList);
		m.addAttribute("mvo",mvo);
		m.addAttribute("itemList",itemList);
		return "/storage/item";
	}
	
	@ResponseBody
	@PostMapping("/use-item")
	public String useItemInStorage(@RequestBody ItemStorageVO itemStorageVO) {
		int storageId = Integer.parseInt(itemStorageVO.getStorageId());
		ProductVO pvo = storageService.getProductByStorageId(storageId);
		storageService.removeItemByStorageId(storageId);
		int pokemonId = getRandomPokemonId(pvo);
		String memberId = itemStorageVO.getMemberId();
		storageService.addPokemon(memberId,pokemonId);
		return "OK";
	}
	
	public int getRandomPokemonId(ProductVO pvo) {
		String[] arr = pvo.getContent().split(",");
		int randomIndex = (int)(Math.random()*arr.length);
		return Integer.parseInt(arr[randomIndex]);
	}
}
