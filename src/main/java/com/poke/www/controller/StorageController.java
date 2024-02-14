package com.poke.www.controller;

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
import com.poke.www.domain.PokemonStorageVO;
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
		m.addAttribute("mvo",mvo);
		
		return "/storage/item";
	}
	
	@ResponseBody
	@PostMapping("/use-item")
	public int useItemInStorage(@RequestBody ItemStorageVO itemStorageVO) {
		int storageId = itemStorageVO.getStorageId();
		ProductVO pvo = storageService.getProductByStorageId(storageId);
		String memberId = itemStorageVO.getMemberId();
		
		
		storageService.removeItemByStorageId(storageId);
		
		
		int pokemonId = getRandomPokemonId(pvo);
		
		
		storageService.addPokemon(memberId,pokemonId);
		return pokemonId;
	}
	
	public int getRandomPokemonId(ProductVO pvo) {
		String[] arr = pvo.getContent().split(",");
		int randomIndex = (int)(Math.random()*arr.length);
		return Integer.parseInt(arr[randomIndex]);
	}
	
	@ResponseBody
	@GetMapping("/item-list/{memberId}")
	public List<ItemStorageVO> getItemList(@PathVariable("memberId") String memberId) {
		List<ItemStorageVO> list = storageService.getItemsByMemberId(memberId);
		return list;
	}
	
	@GetMapping("/{memberId}/pokemon")
	public String getPokemonStorage(Model m, @PathVariable("memberId") String memberId) {
		MemberVO mvo = memberService.getMember(memberId);
		if(mvo==null) {
			return "error";
		}
		m.addAttribute("mvo",mvo);
		return "/storage/pokemon";
	}
	
	@ResponseBody
	@GetMapping("/pokemon-list/{memberId}")
	public List<PokemonStorageVO> getPokemonList(@PathVariable("memberId") String memberId) {
		List<PokemonStorageVO> list = storageService.getPokemonsByMemberId(memberId);
		return list;
	}
	

}
