package com.myWeb.www.service;

import java.util.List;

import com.myWeb.www.domain.CommentVO;
import com.myWeb.www.domain.PagingVO;
import com.myWeb.www.handler.PagingHandler;

public interface CommentService {

	int post(CommentVO cvo);

	List<PagingHandler> getList(int bno, PagingVO pgvo);

}
