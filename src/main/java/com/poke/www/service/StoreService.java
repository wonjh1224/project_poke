package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.ProductVO;

public interface StoreService {

	List<ProductVO> getProducts();

	ProductVO getProduct(int productId);

}
