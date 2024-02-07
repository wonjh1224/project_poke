package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.FileVO;

@Mapper
public interface FileMapper {

	void insertFile(FileVO fvo);

	void deleteFile(long bno);

	List<FileVO> selectFile(long bno);

}
