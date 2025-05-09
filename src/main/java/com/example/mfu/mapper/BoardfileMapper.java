package com.example.mfu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.example.mfu.dto.Boardfile;

@Mapper
public interface BoardfileMapper {
	int insertBoardFile(Boardfile boardfile);
	List<MultipartFile> selectBoardFile(int boardNo);
	int deleteFileOne(String filename);
	int deleteFile(int boardNo);
}
