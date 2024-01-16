package com.myWeb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myWeb.www.security.MemberVO;
import com.myWeb.www.service.MemberService;


@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	@Inject
	private MemberService msv;
	
	@Inject
	private BCryptPasswordEncoder bcEncoder;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo, Model m) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isOk = msv.register(mvo);
		m.addAttribute("msg_signUp", isOk);
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		// 로그인 실패시 다시 로그인 페이지로 돌아와 오류 메시지 전송
		// 다시 로그인 유도
		re.addFlashAttribute("email", request.getAttribute("email"));
		re.addFlashAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login";
	}
	
	@GetMapping("/modify")
	public void modify() {}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, Model m, HttpServletResponse response, HttpServletRequest request) {
		System.out.println(mvo.toString());
		if(mvo.getPwd().isEmpty()) {
			// 비번없는 업데이트 진행
			mvo.setPwd(msv.getPassword(mvo));
		} else {
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		}
		int isOk = msv.modify(mvo);
		m.addAttribute("msg_memMod", isOk);
		Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		// 현재 로그인된 Authentication 객체
		
		new SecurityContextLogoutHandler().logout(request, response, authentication);
		return "/member/login";
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		List <MemberVO> mlist = msv.getMemList();
		for(MemberVO temp : mlist) {
			System.out.println(temp);
		}
		m.addAttribute("mlist", mlist);
	}
	
}
