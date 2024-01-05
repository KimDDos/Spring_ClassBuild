package com.myWeb.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myWeb.www.domain.BoardVO;
import com.myWeb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @RequiredArgsConstructor 선언 후
// private final 로 객체 등록 => 생성자 주입

@Slf4j
@RequestMapping("/board/*")
@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, Model m) {
		log.info(">>> register bvo >>> " + bvo);
		
		int isOk = bsv.insert(bvo);
		log.info("board register ", isOk > 0 ? "Ok":"Fail" );
		
		m.addAttribute("msg_register", isOk);
		return "index";
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		List <BoardVO> list = bsv.getList(); 
		log.info(">>> list >>> {}", list);
		m.addAttribute("list", list);
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(HttpServletRequest request ,Model m, @RequestParam("bno") int bno) {
		String path = request.getServletPath();
		BoardVO bvo = bsv.getDetail(bno, path);
		
		m.addAttribute("bvo", bvo);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re) {
		int isOk = bsv.modify(bvo);
		re.addFlashAttribute("msg_modify", isOk);
		re.addAttribute("bno", bvo.getBno());
		return "redirect:/board/detail";
	}
	
	@GetMapping("/delete")
	public String delete(Model m, @RequestParam("bno") int bno) {
		int isOk = bsv.deleteBoard(bno);
		m.addAttribute("msg_delete", isOk);
		return "index";
	}
	
}
