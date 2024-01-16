package com.myWeb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myWeb.www.domain.CommentVO;
import com.myWeb.www.domain.PagingVO;
import com.myWeb.www.handler.PagingHandler;
import com.myWeb.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/comment/*")
@RestController
public class CommentController {

	@Inject
	private CommentService csv;
	
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		int isOk = csv.post(cvo);
		return (isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@GetMapping(value = "/{bnoVal}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> getList(@PathVariable("bnoVal") long bno, @PathVariable("page") int page){
		PagingVO pgvo = new PagingVO(page, 5);
		PagingHandler ph = csv.getList(bno, pgvo);
		return new ResponseEntity<PagingHandler>(ph, HttpStatus.OK);
	}
	
	@PutMapping(value = "/edit", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@RequestBody CommentVO cvo){
		int isOk = csv.modifyComment(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@DeleteMapping(value = "/del/{cno}/{writer}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> delete(@PathVariable("cno") int cno, @PathVariable("writer") String writer){
		int isOk = csv.delete(cno, writer);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}
