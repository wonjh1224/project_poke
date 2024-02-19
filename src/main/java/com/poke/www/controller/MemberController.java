package com.poke.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poke.www.domain.MemberVO;
import com.poke.www.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/register/test")
	public void registerTest() {
		for(int i=100; i<150; i++) {
			MemberVO mvo = new MemberVO();
			mvo.setMemberId("test"+i);
			mvo.setPassword("test"+i);
			mvo.setName("test"+i);
			mvo.setBirth("2000-01-01");
			mvo.setEmail("test"+i);
			mvo.setPhone("test"+i);
			int isOk = memberService.register(mvo);
		}
	}
	
	@GetMapping("/register")
	public String getRegisterForm() {
		return "/member/register";
	}
	@PostMapping("/register")
	public String memberRegister(MemberVO mvo) {
		int isOk = memberService.register(mvo);
		return "redirect:/";
	}
	@GetMapping("/login")
	public String getLoginForm() {
		
		return "/member/login";
	}
	@PostMapping("/login")
	public String memberLogin(MemberVO mvo, HttpServletRequest request) {
		MemberVO loginMember = memberService.login(mvo.getMemberId(),mvo.getPassword());
		if(loginMember == null) {
			return "/member/login";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("loginMemberId", loginMember.getMemberId());
		
		return "redirect:/";
	}
	@PostMapping("/logout")
	public String memberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/member")
	public MemberVO getMember(@RequestBody MemberVO mvo) {
		return memberService.getMember(mvo.getMemberId());
	}
	
	
}
