package com.myWeb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myWeb.www.domain.CommentVO;
import com.myWeb.www.domain.PagingVO;
import com.myWeb.www.handler.PagingHandler;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> getList(@Param("bno")long bno, @Param("pgvo")PagingVO pgvo);

	int selectOneBnoTotalCount(long bno);

	int modify(CommentVO cvo);

	int deleteComment(int cno);

}
