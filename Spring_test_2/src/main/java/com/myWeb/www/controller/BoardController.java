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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myWeb.www.domain.BoardDTO;
import com.myWeb.www.domain.BoardVO;
import com.myWeb.www.domain.FileVO;
import com.myWeb.www.domain.PagingVO;
import com.myWeb.www.handler.FileHandler;
import com.myWeb.www.handler.PagingHandler;
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

	private final FileHandler fh;
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, Model m, @RequestParam(name= "files", required = false) MultipartFile[] files) {
		log.info(">>> register bvo >>> " + bvo);
		
		List<FileVO> flist = null;
		
		// fileHandler 생성  (인풋 : multipartfile) => (아웃풋 : flist)
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		int isOk = bsv.insert(new BoardDTO(bvo, flist));
		log.info("board register ", isOk > 0 ? "Ok":"Fail" );
		
		m.addAttribute("msg_register", isOk);
		return "index";
	}
	
	@GetMapping("/list")
	public void list(Model m, PagingVO pgvo) {
		log.info(">>>>>>>>>> list pgvo >>>>>> {}", pgvo);
		// 페이징 처리
		List <BoardVO> list = bsv.getList(pgvo); 
		// totalCount 구하기
		int totalCount = bsv.getTotalCount(pgvo);
		log.info(">>>> totalCount >>> {}", totalCount);
		
		log.info(">>> list >>> {}", list);
		m.addAttribute("ph", new PagingHandler(pgvo, totalCount));
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
