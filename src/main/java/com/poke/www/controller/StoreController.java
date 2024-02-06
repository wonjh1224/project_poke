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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	
	@GetMapping("/{productId}/purchase")
	public String getProductPurchaseForm(Model m, @PathVariable("productId") int productId) {
		ProductVO pvo = ssv.getProduct(productId);
		m.addAttribute("pvo",pvo);
		return "/store/purchase";
	}
	
	@PostMapping("/purchase")
	public String purchase(@RequestParam("productId") int productId, @RequestParam("memberId") String memberId, HttpServletRequest request) {
		MemberVO mvo = msv.getMember(memberId);
		ProductVO pvo = ssv.getProduct(productId);
		
		//잔액<금액이면 오류페이지로 (추후 추가예정)
		if(mvo.getPoint()<pvo.getPrice()) {
			return "error";
		}
		
		//결제처리 (내 돈 - 가격)
		msv.subtractPriceFromMemberPoint(memberId,pvo.getPrice());
		
		//(보관함에 구매한 상품 추가)
		storageService.addItem(memberId,productId);
		
		
		//세션정보 업데이트
		mvo = msv.getMember(memberId);
		HttpSession session = request.getSession(false);
		session.setAttribute("loginMember", mvo);
		
		
		return "redirect:/";
	}
}
