package com.myWeb.www.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.myWeb.www.domain.BoardDTO;
import com.myWeb.www.domain.BoardVO;
import com.myWeb.www.domain.FileVO;
import com.myWeb.www.domain.PagingVO;
import com.myWeb.www.repository.BoardDAO;
import com.myWeb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardDAO bdao;
	
	private final FileDAO fdao;
	
	HttpServletRequest request;

	@Override
	public int insert(BoardDTO bdto) {
		// TODO Auto-generated method stub
		int isOk = bdao.insert(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		// bvo insert 후 파일도 있다면
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			// bno setting
			long bno = bdao.selectOneBno();
			// 가장 마지막에 등록된 bno
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				fdao.insert(fvo);
			}
		}
		
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVO getDetail(int bno, String path) {
		if(path.equals("/board/detail")) {
			readCountUp(bno);
		}
		return bdao.getDetail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.boardUpdate(bvo);
	}

	@Override
	public int deleteBoard(int bno) {
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}

	@Override
	public int readCountUp(int bno) {
		// TODO Auto-generated method stub
		return bdao.upCount(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pgvo);
	} 
	
}
