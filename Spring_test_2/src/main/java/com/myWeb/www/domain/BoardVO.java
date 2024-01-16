package com.myWeb.www.domain;

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
	
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regAt="
				+ regAt + ", modAt=" + modAt + ", readCount=" + readCount + ", cmtQty=" + cmtQty + ", hasFile="
				+ hasFile + "]";
	}

	public BoardVO() {
		super();
	}

	public BoardVO(long bno, String title, String writer, String content, String regAt, String modAt, int readCount,
			int cmtQty, int hasFile) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regAt = regAt;
		this.modAt = modAt;
		this.readCount = readCount;
		this.cmtQty = cmtQty;
		this.hasFile = hasFile;
	}

	public long getBno() {
		return bno;
	}

	public void setBno(long bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegAt() {
		return regAt;
	}

	public void setRegAt(String regAt) {
		this.regAt = regAt;
	}

	public String getModAt() {
		return modAt;
	}

	public void setModAt(String modAt) {
		this.modAt = modAt;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getCmtQty() {
		return cmtQty;
	}

	public void setCmtQty(int cmtQty) {
		this.cmtQty = cmtQty;
	}

	public int getHasFile() {
		return hasFile;
	}

	public void setHasFile(int hasFile) {
		this.hasFile = hasFile;
	}
	
	// CamelCase가 적용되기 때문에 _(언더바)는 반영하지 않음
	
	
	
}
