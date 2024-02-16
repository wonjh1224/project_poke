package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.OrderProductVO;
import com.poke.www.repository.MyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyServiceImpl implements MyService {
	private final MyMapper mapper;

	@Override
	public List<OrderProductVO> getOrderProductList(String loginMemberId) {
		return mapper.selectOrderProductList(loginMemberId);
	}

	@Override
	public int addChargeHistory(String memberId, int point) {
		return mapper.insertChargeHistory(memberId,point);
	}
}
