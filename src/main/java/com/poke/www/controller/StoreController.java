package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poke.www.domain.MemberVO;
import com.poke.www.domain.ProductVO;
import com.poke.www.service.MemberService;
import com.poke.www.service.StorageService;
import com.poke.www.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
	private final StoreService ssv;
	private final MemberService msv;
	private final StorageService storageService;
	
	@GetMapping
	public String getStore(Model m) {
		List<ProductVO> list = ssv.getProducts();
		m.addAttribute("list",list);
		return "/store/store";
	}
	
	@GetMapping("/{productId}")
	public String getProductDetail(Model m, @PathVariable("productId") int productId) {
		ProductVO pvo = ssv.getProduct(productId);
		m.addAttribute("pvo",pvo);
		return "/store/detail";
	}
	
	@GetMapping("/purchase/{productId}")
	public String getProductPurchaseForm(Model m, @PathVariable("productId") int productId) {
		ProductVO pvo = ssv.getProduct(productId);
		m.addAttribute("pvo",pvo);
		return "/store/purchase";
	}
	
	@PostMapping("/purchase")
	public String purchase(@RequestParam("productId") int productId, @RequestParam("memberId") String memberId) {

		MemberVO mvo = msv.getMember(memberId);
		ProductVO pvo = ssv.getProduct(productId);
		
		//추후 추가 예정
		if(mvo.getPoint()<pvo.getPrice()) {
			return "error";
		}
		
		msv.subtractPriceFromMemberPoint(memberId,pvo.getPrice());
		storageService.addItem(memberId,productId);
		
		return "redirect:/";
	}
}
