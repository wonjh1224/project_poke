package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poke.www.domain.ProductVO;
import com.poke.www.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
	private final StoreService ssv;
	
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
		
		return "/store/purchase";
	}
}
