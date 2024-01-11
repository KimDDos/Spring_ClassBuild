package com.myWeb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myWeb.www.domain.CommentVO;
import com.myWeb.www.domain.PagingVO;
import com.myWeb.www.handler.PagingHandler;
import com.myWeb.www.repository.BoardDAO;
import com.myWeb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentDAO cdao;
	
	private final BoardDAO bdao;

	@Override
	public int post(CommentVO cvo) {
		// TODO Auto-generated method stub
		commentCountUp(cvo.getBno());
		return cdao.insert(cvo);
	}

	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// TODO Auto-generated method stub
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		List<CommentVO> list = cdao.getList(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}

	@Override
	public int modifyComment(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.modify(cvo);
	}

	@Override
	public int delete(int cno, String writer) {
		// TODO Auto-generated method stub
		return cdao.deleteComment(cno);
	}

	@Override
	public void commentCountUp(long bno) {
		// TODO Auto-generated method stub
		bdao.commentCountUp(bno);
	}
	
}
