package com.myWeb.www.service;

import java.util.List;

import com.myWeb.www.domain.BoardDTO;
import com.myWeb.www.domain.BoardVO;
import com.myWeb.www.domain.PagingVO;

public interface BoardService {

	int insert(BoardDTO boardDTO);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno, String path);

	int modify(BoardVO bvo);

	int deleteBoard(int bno);
	
	int readCountUp(int bno);

	int getTotalCount(PagingVO pgvo);

}
