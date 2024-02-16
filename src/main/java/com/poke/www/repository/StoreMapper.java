package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.ProductVO;

@Mapper
public interface StoreMapper {

	List<ProductVO> selectProducts();

	ProductVO selectProductById(int productId);

	int insertPurchaseHistory(String memberId, int productId);

}
