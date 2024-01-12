package com.myWeb.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myWeb.www.security.MemberVO;
import com.myWeb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService msv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo, Model m) {
		log.info(" >>>> member mvo >>>> ", mvo);
		int isOk = msv.register(mvo);
		m.addAttribute("msg_login", isOk);
		return "/index";
	}
	
}
