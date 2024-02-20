package com.poke.www.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/register")
	public String getRegisterForm() {
		return "/member/register";
	}
	@PostMapping("/register")
	public String memberRegister(MemberVO mvo,
			@RequestParam(name="profile", required = false)MultipartFile profile) throws IOException {
		memberService.register(mvo);
		if(!profile.isEmpty()) {
			String path = "C:\\_poke\\_project\\_fileUpload\\profile\\"+mvo.getMemberId()+".png";
			profile.transferTo(new File(path));
			memberService.modifyHasProfile(mvo.getMemberId(),true);
		}
		return "redirect:/";
	}
	@GetMapping("/login")
	public String getLoginForm(Model m, @RequestParam(name="rd-url", defaultValue = "/")String url) {
		m.addAttribute("url",url);
		return "/member/login";
	}
	@PostMapping("/login")
	public String memberLogin(MemberVO mvo, HttpServletRequest request,
			@RequestParam(name="rd-url", defaultValue = "/")String url) {
		MemberVO loginMember = memberService.login(mvo.getMemberId(),mvo.getPassword());
		if(loginMember == null) {
			return "/member/login";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("loginMemberId", loginMember.getMemberId());
		log.info("url url url url : {}",url);
		return "redirect:" + url;
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
