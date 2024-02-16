package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.OrderProductVO;

@Mapper
public interface MyMapper {

	List<OrderProductVO> selectOrderProductList(String loginMemberId);

	int insertChargeHistory(String memberId, int point);

}
