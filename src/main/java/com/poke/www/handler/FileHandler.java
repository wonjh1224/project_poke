package com.poke.www.handler;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.poke.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //사용자 클래스 빈으로 등록
public class FileHandler {
	
	private final String UP_DIR = "C:\\_poke\\_project\\_fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		
		List<FileVO> flist = new ArrayList<>();
		
		LocalDate date = LocalDate.now(); // 2024-02-06
		String today = date.toString();
		today = today.replace("-", File.separator); // 2024/02/06
		
		//C:\_poke\_project\_fileUpload\2024\02\06
		File folders = new File(UP_DIR, today);
		
		//폴더 생성
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		//files 객체에 대한 설정
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			log.info(">>> origin >>> {}", originalFileName);
			String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			log.info(">>> fileNmae >>> {}", fileName);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			String uuidStr = uuid.toString();
			fvo.setUuid(uuidStr);
			//기본 fvo setting 완료 (saveDir, fileSize, fileName, uuid)
			
			//디스크에 저장 할 파일 객체를 생성
			String fullFileName = uuidStr + "_" + fileName;
			File storeFile = new File(folders, fullFileName);
			//C:\_poke\_project\_fileUpload\2024\02\06\fullFileName
			
			try {
				file.transferTo(storeFile); //저장
			} catch (Exception e) {
				e.printStackTrace();
				log.info("파일 생성 오류");
			}
			
			flist.add(fvo);
			
		}
		
		return flist;	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
