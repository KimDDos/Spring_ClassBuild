package com.myWeb.www.repository;

import java.util.List;

import com.myWeb.www.domain.BoardVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	int boardUpdate(BoardVO bvo);

	int delete(int bno);

	int upCount(int bno);
	
}
