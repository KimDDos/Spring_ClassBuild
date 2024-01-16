package com.myWeb.www.domain;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

public class PagingVO {

	private int pageNo;  // 현재 페이지 번호
	private int qty;  // 한 페이지당 표시된 리스트 개수
	
	private String type;
	private String keyword;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	// 시작번지 구하기
	public int getPageStart() {
		return(this.pageNo - 1) * this.qty;
	}
	
	// type의 값을 배열로 생성
	// 복합타입의 키워드일 경우 각자 검색해야하기 때문에 배열로 생성
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "PagingVO [pageNo=" + pageNo + ", qty=" + qty + ", type=" + type + ", keyword=" + keyword + "]";
	}
	
	
	
	
}
