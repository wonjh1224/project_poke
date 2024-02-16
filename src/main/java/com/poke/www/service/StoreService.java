package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.ProductVO;

public interface StoreService {

	List<ProductVO> getProducts();

	ProductVO getProductByProductId(int productId);

	int addHistory(String memberId, int productId);

}
