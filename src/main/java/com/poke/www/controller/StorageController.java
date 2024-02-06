package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poke.www.domain.ItemStorageVO;
import com.poke.www.domain.MemberVO;
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
		m.addAttribute("memberId");
		m.addAttribute("itemList",itemList);
		return "/storage/item";
	}
	
}
