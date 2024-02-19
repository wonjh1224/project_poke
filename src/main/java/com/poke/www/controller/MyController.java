package com.poke.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.poke.www.domain.MarketItemVO;
import com.poke.www.domain.MemberVO;
import com.poke.www.domain.OrderVO;
import com.poke.www.domain.TradeVO;
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
	public String getChargePage(Model m,@SessionAttribute("loginMemberId") String loginMemberId,
			@RequestParam(name="rd-url",required=false)String url) {

		m.addAttribute("loginMemberId",loginMemberId);
		if(url!=null) {
			m.addAttribute("url",url);			
		}
		return "/my/charge";
	}
	
	@ResponseBody
	@PostMapping("/charge")
	public int chargePoint(@RequestBody MemberVO mvo) {
		String memberId = mvo.getMemberId();
		int point = mvo.getPoint();
		memberService.modifyPointByMemberId(memberId, point);
		myService.addChargeHistory(memberId,point);
		return point;
	}
	
	@GetMapping({"/purchases","/purchases/packs"})
	public String getPurchasesPacksPage(Model m,
			@SessionAttribute("loginMemberId") String loginMemberId) {
		List<OrderVO> orderList = myService.getOrderProductList(loginMemberId);
		m.addAttribute("orderList",orderList);
		return "/my/purchasesPacks";
	}
	@GetMapping("/purchases/points")
	public String getPurchasesPointsPage(Model m,
			@SessionAttribute("loginMemberId") String loginMemberId) {
		List<OrderVO> orderList = myService.getOrderPointList(loginMemberId);
		m.addAttribute("orderList",orderList);
		return "/my/purchasesPoints";
	}
	@GetMapping("/market/on-sale")
	public String getSalesPoketmonsPage(Model m,
			@SessionAttribute("loginMemberId") String loginMemberId) {
		m.addAttribute("loginMemberId",loginMemberId);
		return "/my/onSale";
	}
	
	@ResponseBody
	@PostMapping("/market/on-sale/list")
	public List<MarketItemVO> getItemList(@RequestBody String memberId) {
		return myService.getMarketItemsByMemberId(memberId);
	}
	
	@GetMapping("/market/trade")
	public String getTradePage(Model m,
			@SessionAttribute("loginMemberId") String loginMemberId) {
		List<TradeVO> tradeList = myService.getTradeList(loginMemberId);
		m.addAttribute("tradeList",tradeList);
		return "/my/trade";
	}
	
	@GetMapping("/account")
	public String getAccountPage() {
		return "/my/account";
	}
	
	@ResponseBody
	@PostMapping("/tmp-profile")
	public String saveTemporaryProfileImage(@RequestParam("image") MultipartFile file) throws IOException {
		
		if (!file.isEmpty()) {
			String path = "C:\\_poke\\_project\\_fileUpload\\profile\\tmp\\"+file.getOriginalFilename();
			file.transferTo(new File(path));
			return "profile\\tmp\\"+file.getOriginalFilename();
		}
		return null;
	}
	@PutMapping("/profile")
	public String modifyProfileImage(
			@RequestParam(name="image", required=false) MultipartFile file,
			@RequestParam("memberId") String memberId,
			@RequestParam("isDefault") String isDefault) throws IOException {
		if (file!=null) {
			String path = "C:\\_poke\\_project\\_fileUpload\\profile\\"+memberId+".png";
			file.transferTo(new File(path));
		}else {
			log.info("파일 비었음 파일 비었음 파일 비었음 @@@@@@{}",isDefault);
		}
		return "/my/account";
	}
	
}
