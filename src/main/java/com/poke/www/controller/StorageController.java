package com.poke.www.controller;

import java.util.ArrayList;
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
import com.poke.www.domain.PokedexVO;
import com.poke.www.domain.PokemonStorageVO;
import com.poke.www.domain.PokemonVO;
import com.poke.www.domain.ProductVO;
import com.poke.www.service.MemberService;
import com.poke.www.service.PokedexService;
import com.poke.www.service.PokemonService;
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
	private final PokemonService pokemonService;
	private final PokedexService pokedexService;
	
	@GetMapping({"/{memberId}/item","/{memberId}"})
	public String getStorage(Model m,
//			@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
			@PathVariable("memberId") String memberId) {
		MemberVO mvo = memberService.getMember(memberId);
		if(mvo==null) {
			return "error";
		}
		m.addAttribute("mvo",mvo);
		
		return "storage/item";
	}
	
	/**
	 * 카드팩 사용 메서드
	 * 파라미터로 해당 카드팩의 사용자(memberId), 보관함 PK(storageId)를 받고
	 * storageId로 해당 카드팩의 상품 정보(ProductVO)를 받아 온 후(storageService.getProductByStorageId)
	 * content(1,2,3, ... ,151)에서 랜덤 포켓몬 번호(pokemonId)를 뽑아 (getRandomPokemonId(pvo))
	 * 포켓몬 보관함에 memberId와 pokemonId를 넣어준다(storageService.addPokemon)
	 * 사용 후 카드팩은 DB에서 삭제
	 * 
	 * @param itemStorageVO : memberId, storageId
	 * @return 카드팩 사용 후 결과물을 List<PokemonVO> 타입으로 리턴
	 */
	@ResponseBody
	@PostMapping("/use-item")
	public List<PokemonVO> useItemInStorage(@RequestBody ItemStorageVO itemStorageVO) {
		int storageId = itemStorageVO.getStorageId();
		String memberId = itemStorageVO.getMemberId();
		ProductVO pvo = storageService.getProductByStorageId(storageId);
		//카드팩 내용물 개수
		int numberOfCards = pvo.getNumberOfCards();
		
		//포켓몬 추가 로직
		List<PokemonVO> pokemonList = new ArrayList<>();
		for(int i=0; i<numberOfCards; i++) {
			int pokemonId = getRandomPokemonId(pvo);
			pokemonList.add(pokemonService.getPokemonByPokemonId(pokemonId));
			storageService.addPokemon(memberId,pokemonId);			
		}
		
		//카드팩 사용 후 삭제
		storageService.removeItemByStorageId(storageId);	
		log.info("list list list{}",pokemonList);
		return pokemonList;
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
		return "storage/pokemon";
	}
	
	@ResponseBody
	@GetMapping("/pokemon-list/{memberId}")
	public List<PokemonStorageVO> getPokemonList(@PathVariable("memberId") String memberId) {
		List<PokemonStorageVO> list = storageService.getPokemonsByMemberId(memberId);
		return list;
	}
	@ResponseBody
	@GetMapping("/not-added-pokemon-list/{memberId}")
	public List<PokemonStorageVO> getNotAddedPokemonList(@PathVariable("memberId") String memberId) {
		List<PokedexVO> pokedexList = pokedexService.getPokemonsByMemberId(memberId);
		
		//도감에 등록된 포켓몬이 없을 경우
		if(pokedexList.size() == 0) {
			return storageService.getPokemonsByMemberId(memberId);
		}
		
		String addedPokemonIds = "";
		for(PokedexVO pokemon : pokedexList) {
			addedPokemonIds += pokemon.getPokemonId()+",";
		}
		addedPokemonIds = addedPokemonIds.substring(0,addedPokemonIds.length()-1);
		
		List<PokemonStorageVO> pokemonList = storageService.getPokemonsByMemberIdAndNotInPokemonIds(memberId,addedPokemonIds); 
		
		return pokemonList;
	}
	
	
	

}
