package com.poke.www.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageMapper {

	int insertItem(String memberId, int productId);

}
