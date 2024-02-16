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
import com.poke.www.domain.OrderVO;
import com.poke.www.service.MemberService;
import com.poke.www.service.MyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/my")
public class MyController {
	private final MyService myService;
	private final MemberService memberService;
	@GetMapping("/charge")
	private String getChargePage(Model m,@SessionAttribute("loginMemberId") String loginMemberId,
			@RequestParam(name="rd-url",required=false)String url) {

		m.addAttribute("loginMemberId",loginMemberId);
		if(url!=null) {
			m.addAttribute("url",url);			
		}
		return "/my/charge";
	}
	
	@ResponseBody
	@PostMapping("/charge")
	private int chargePoint(@RequestBody MemberVO mvo) {
		String memberId = mvo.getMemberId();
		int point = mvo.getPoint();
		memberService.modifyPointByMemberId(memberId, point);
		myService.addChargeHistory(memberId,point);
		return point;
	}
	
	@GetMapping({"/purchases","/purchases/packs"})
	private String getPurchasesPacksPage(Model m,
			@SessionAttribute("loginMemberId") String loginMemberId) {
		List<OrderVO> orderList = myService.getOrderProductList(loginMemberId);
		m.addAttribute("orderList",orderList);
		return "/my/purchasesPacks";
	}
	@GetMapping("/purchases/points")
	private String getPurchasesPointsPage(Model m,
			@SessionAttribute("loginMemberId") String loginMemberId) {
		List<OrderVO> orderList = myService.getOrderPointList(loginMemberId);
		m.addAttribute("orderList",orderList);
		return "/my/purchasesPoints";
	}
	@GetMapping("/sales/on-sale")
	private String getSalesPoketmonsPage(Model m,
			@SessionAttribute("loginMemberId") String loginMemberId) {
		List<MarketItemVO> itemList = myService.getMarketItemsByMemberId(loginMemberId);
		m.addAttribute("itemList",itemList);
		return "/my/onSale";
	}
}
