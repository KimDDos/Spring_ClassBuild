package com.myWeb.www.domain;

import java.util.List;

public class BoardDTO {

	private BoardVO bvo;
	private List<FileVO> flist;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public BoardDTO(BoardVO bvo, List<FileVO> flist) {
		super();
		this.bvo = bvo;
		this.flist = flist;
	}

	public BoardDTO() {
		super();
	}

	public BoardVO getBvo() {
		return bvo;
	}

	public void setBvo(BoardVO bvo) {
		this.bvo = bvo;
	}

	public List<FileVO> getFlist() {
		return flist;
	}

	public void setFlist(List<FileVO> flist) {
		this.flist = flist;
	}
	
	
	
	
}
