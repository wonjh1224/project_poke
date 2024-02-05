package com.poke.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	private final MemberService msv;
	
	@GetMapping("/register")
	public String getRegisterForm() {
		return "/member/register";
	}
	@PostMapping("/register")
	public String memberRegister(MemberVO mvo) {
		int isOk = msv.register(mvo);
		return "redirect:/";
	}
	@GetMapping("/login")
	public String getLoginForm() {
		return "/member/login";
	}
	@PostMapping("/login")
	public String memberLogin(MemberVO mvo, HttpServletRequest request) {
		MemberVO loginMember = msv.login(mvo.getMemberId(),mvo.getPassword());
		if(loginMember == null) {
			return "/member/login";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", loginMember);
		
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
	
}
