package com.myWeb.www.service;

import com.myWeb.www.domain.CommentVO;
import com.myWeb.www.domain.PagingVO;
import com.myWeb.www.handler.PagingHandler;

public interface CommentService {

	int post(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);

	int modifyComment(CommentVO cvo);

	int delete(int cno, String writer);

}
