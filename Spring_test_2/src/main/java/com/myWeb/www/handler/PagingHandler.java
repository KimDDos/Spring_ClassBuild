package com.myWeb.www.handler;

import java.util.List;

import com.myWeb.www.domain.CommentVO;
import com.myWeb.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingHandler {
	
	private int startPage; // 하단 페이지 네이션의 시작번호 1, 11, 21 ...
	private int endPage;  // 하단 페이지네이션의 끝 번호 10, 20, 30;
	private boolean prev, next;
	
	private int totalCount;
	private PagingVO pgvo;
	
	private List<CommentVO> cmtList;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		// (1~10) => 10 / 11~20 = 20 ...
		// 1/10.0 >> 0.1 >> Math.ceil로 올림처리하면 1 >> 1*10 >> 10
		// 2/10.0 >> 0.2 >> Math.ceil로 올림처리하면 1 >> 1*10 >> 10
		// 11/10.0 >> 1.1 >> Math.ceil로 올림처리하면 2 >> 2*10 >> 20
		// 21/10.0 >> 2.1 >> Math.ceil로 올림처리하면 3 >> 3*10 >> 30
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)pgvo.getQty()) * pgvo.getQty(); 
		this.startPage = endPage - 9;
		
		// 실제 마지막 페이지
		// 전체 글 수 / 한 페이지에 표시되는 게시글 수 => 올림
		int realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
	
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
	
}
