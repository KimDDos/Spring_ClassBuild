package com.myWeb.www.repository;

import java.util.List;

import com.myWeb.www.domain.BoardVO;
import com.myWeb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int boardUpdate(BoardVO bvo);

	int delete(int bno);

	int upCount(int bno);

	int getTotalCount(PagingVO pgvo);
	
}
