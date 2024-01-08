package com.myWeb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myWeb.www.domain.CommentVO;
import com.myWeb.www.domain.PagingVO;
import com.myWeb.www.handler.PagingHandler;
import com.myWeb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		// TODO Auto-generated method stub
		// bdao에 comment 갯수 업 시켜도 될 듯?
		return cdao.insert(cvo);
	}

	@Override
	public List<PagingHandler> getList(int bno, PagingVO pgvo) {
		// TODO Auto-generated method stub
		return cdao.getList(bno, pgvo);
	}
	
}
