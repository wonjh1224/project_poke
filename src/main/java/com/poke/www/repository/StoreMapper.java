package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.ProductVO;

@Mapper
public interface StoreMapper {

	List<ProductVO> selectProducts();

	ProductVO selectProduct(int productId);

}
