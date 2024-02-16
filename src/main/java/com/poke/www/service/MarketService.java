package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.MarketItemVO;
import com.poke.www.domain.MemberVO;

public interface MarketService {

	List<MarketItemVO> getItems();

	int addItem(MarketItemVO marketItemVO);

	MarketItemVO getItemByItemId(int itemId);

	int removeItemByItemId(int itemId);

	int addTradeHistory(String buyer, String seller, int price, int pokemonId);
	
}
