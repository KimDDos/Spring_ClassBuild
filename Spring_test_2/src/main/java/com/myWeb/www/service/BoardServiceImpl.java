package com.myWeb.www.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myWeb.www.domain.BoardDTO;
import com.myWeb.www.domain.BoardVO;
import com.myWeb.www.domain.FileVO;
import com.myWeb.www.domain.PagingVO;
import com.myWeb.www.repository.BoardDAO;
import com.myWeb.www.repository.CommentDAO;
import com.myWeb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardDAO bdao;
	
	private final FileDAO fdao;
	
	private final CommentDAO cdao;
	
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
		bdao.updateBoardCounts();
		return bdao.getList(pgvo);
	}

	// 두개의 데이터를 들고올경우 트랜잭션 적용
	@Transactional
	@Override
	public BoardDTO getDetail(int bno, String path) {
		if(path.equals("/board/detail123")) {
			readCountUp(bno);
		}
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO> flist = fdao.getFileList(bno);
		BoardDTO bdto = new BoardDTO(bvo, flist);
		return bdto;
	}

	@Transactional
	@Override
	public int modify(BoardDTO bdto) {
		// TODO Auto-generated method stub
		int isOk = bdao.boardUpdate(bdto.getBvo());
		if(bdto.getFlist() == null) {
			return isOk;
		} else {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				fdao.insert(fvo);
			}
		}
		
		return isOk;
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

	@Override
	public int fileDelete(String uuid) {
		// TODO Auto-generated method stub
		return fdao.fileDelete(uuid);
	}

	@Override
	public void fileCountUp(long bno) {
		// TODO Auto-generated method stub
		bdao.fileCountUp(bno);
	} 
	
}
