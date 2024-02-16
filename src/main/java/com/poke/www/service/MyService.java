package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.MarketItemVO;
import com.poke.www.domain.OrderVO;
import com.poke.www.domain.TradeVO;

public interface MyService {

	List<OrderVO> getOrderProductList(String loginMemberId);

	int addChargeHistory(String memberId, int point);

	List<OrderVO> getOrderPointList(String loginMemberId);

	List<MarketItemVO> getMarketItemsByMemberId(String loginMemberId);

	List<TradeVO> getTradeList(String loginMemberId);

}
