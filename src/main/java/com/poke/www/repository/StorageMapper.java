package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.ItemStorageVO;

@Mapper
public interface StorageMapper {

	int insertItem(String memberId, int productId);

	List<ItemStorageVO> selectItemsByMemberId(String memberId);

}
