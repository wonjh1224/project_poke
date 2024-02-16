package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.ProductVO;
import com.poke.www.repository.StoreMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
	private final StoreMapper mapper;

	@Override
	public List<ProductVO> getProducts() {
		return mapper.selectProducts();
	}

	@Override
	public ProductVO getProductByProductId(int productId) {
		// TODO Auto-generated method stub
		return mapper.selectProductById(productId);
	}

	@Override
	public int addPurchaseHistory(String memberId, int productId) {
		return mapper.insertPurchaseHistory(memberId,productId);
	}
}
