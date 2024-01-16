package com.myWeb.www.domain;


public class FileVO {
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private long bno;
	private long fileSize;
	private String regAt;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSaveDir() {
		return saveDir;
	}
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public long getBno() {
		return bno;
	}
	public void setBno(long bno) {
		this.bno = bno;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getRegAt() {
		return regAt;
	}
	public void setRegAt(String regAt) {
		this.regAt = regAt;
	}
	public FileVO(String uuid, String saveDir, String fileName, int fileType, long bno, long fileSize, String regAt) {
		super();
		this.uuid = uuid;
		this.saveDir = saveDir;
		this.fileName = fileName;
		this.fileType = fileType;
		this.bno = bno;
		this.fileSize = fileSize;
		this.regAt = regAt;
	}
	public FileVO() {
		super();
	}
	@Override
	public String toString() {
		return "FileVO [uuid=" + uuid + ", saveDir=" + saveDir + ", fileName=" + fileName + ", fileType=" + fileType
				+ ", bno=" + bno + ", fileSize=" + fileSize + ", regAt=" + regAt + "]";
	}
	
	
	
	
}
