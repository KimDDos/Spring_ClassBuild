package com.myWeb.www.repository;

import java.util.List;

import com.myWeb.www.domain.FileVO;

public interface FileDAO {

	void insert(FileVO fvo);

	List<FileVO> getFileList(int bno);

	int fileDelete(String uuid);
	
}
