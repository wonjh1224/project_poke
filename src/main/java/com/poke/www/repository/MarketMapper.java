package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.MarketItemVO;

@Mapper
public interface MarketMapper {

	List<MarketItemVO> selectItems();

	int insertItem(MarketItemVO marketItemVO);

	MarketItemVO selectItemByItemId(int itemId);

	int deleteItemByItemId(int itemId);
	
}
