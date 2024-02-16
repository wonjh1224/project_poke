package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.OrderProductVO;

public interface MyService {

	List<OrderProductVO> getOrderProductList(String loginMemberId);

	int addChargeHistory(String memberId, int point);

}
