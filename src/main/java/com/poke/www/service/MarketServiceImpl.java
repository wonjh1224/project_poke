package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.MarketItemVO;
import com.poke.www.domain.MemberVO;
import com.poke.www.repository.MarketMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarketServiceImpl implements MarketService{
	private final MarketMapper mapper;

	@Override
	public List<MarketItemVO> getItems() {
		return mapper.selectItems();
	}

	@Override
	public int addItem(MarketItemVO marketItemVO) {
		return mapper.insertItem(marketItemVO);
	}

	@Override
	public MarketItemVO getItemByItemId(int itemId) {
		return mapper.selectItemByItemId(itemId);
	}

	@Override
	public int removeItemByItemId(int itemId) {
		return mapper.deleteItemByItemId(itemId);
	}

	@Override
	public int addTradeHistory(String buyer, String seller, int price, int pokemonId) {
		return mapper.insertTradeHistory(buyer,seller,price,pokemonId);
	}
}
