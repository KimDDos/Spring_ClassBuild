package com.myWeb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardVO {
	
	private long bno;
	private String title;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
	private int readCount;
	private int cmtQty;
	private int hasFile;
	
	// CamelCase가 적용되기 때문에 _(언더바)는 반영하지 않음
	
}
