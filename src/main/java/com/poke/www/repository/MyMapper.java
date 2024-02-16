package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.MarketItemVO;
import com.poke.www.domain.OrderVO;

@Mapper
public interface MyMapper {

	List<OrderVO> selectOrderProductList(String loginMemberId);

	int insertChargeHistory(String memberId, int point);

	List<OrderVO> selectOrderPointList(String loginMemberId);

	List<MarketItemVO> selectMarketItemsByMemberId(String loginMemberId);

}
