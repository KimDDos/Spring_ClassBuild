package com.myWeb.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myWeb.www.domain.FileVO;
import com.myWeb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class FIleSweeper {
	
	private final String BASE_PATH ="D:\\KDY\\_myProject\\_java\\_fileUpload\\";
	
	private final FileDAO fdao;
	
	// cron 표현식 : 초 / 분 / 시 / 일 / 월 / 요일 / 년도(생략가능)
	// [예시]
	// 1. 0 0 12 * * * : 매일 12시에 실행 
	// 2. 0 15 10 * * * : 매일 10시 15분에 실행
	// 3. 0 0 20 ? * MON-FRI : 매일 10시 15분에 실행
	
	@Scheduled(cron="0 41 10 * * *")
	public void FileSweeper() {
		log.info(">>>> FileSweeper Running Start~!! : {}", LocalDateTime.now());
		
		// DB에 등록된 파일 목록 가져오기
		List <FileVO> dbList = fdao.selectListAllFile();
		
		// 저장소를 검색할 때 필요한 파일의 경로 리스트(실제 존재해야 될 리스트)
		List <String> currFiles = new ArrayList<String>();
		
		for(FileVO fvo : dbList) {
			String filePath = fvo.getSaveDir() + File.separator + fvo.getUuid();
			String fileName = fvo.getFileName();
			currFiles.add(BASE_PATH+filePath+"_"+fileName);
			
			// 이미지라면 썸네일 경로도 추가
			if(fvo.getFileType() > 0) {
				currFiles.add(BASE_PATH+filePath+"_th_"+fileName);
			}
		}
		
		log.info("currFiles >>> {}", currFiles);;
		
		// 오늘 날짜를 반영한 폴더 구조 경로 만들기
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);
		
		// 경로를 기반으로 저장되어 있는 파일을 검색
		// 오늘 날짜 폴더 안에 있는 전체 파일
		File dir = Paths.get(BASE_PATH + today).toFile();
		File[] allFileObjects = dir.listFiles();
		// listFile 메서드는 배열로만 리턴을 함! 리스트를 쓸 수가 없음
		
		//실제 저장되어 있는 파일과 DB에 존재하는 파일을 비교하여 없는 파일은 삭제 진행
		for(File file : allFileObjects) {
			log.info("File file 객체 >>>>>> {}", file);
			String storedFileName = file.toPath().toString();
			// 실제 저장한 파일의 경로를 보는거? Yes Or No =>
			if(!currFiles.contains(storedFileName)) {
				file.delete();
				log.info(">>>> delete file >>> {}", storedFileName);
			}
		}
		
		log.info(">>>> FileSweeper Running Finish~!! : {}", LocalDateTime.now());
		// start : 2024-01-12T10:41:00.007693100
		// finish : 2024-01-12T10:41:00.058437700
		// 0.051 sec
	}
	
}
