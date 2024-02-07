package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.ItemStorageVO;
import com.poke.www.domain.ProductVO;

public interface StorageService {

	int addItem(String memberId, int productId);

	List<ItemStorageVO> getItemsByMemberId(String memberId);

	int removeItemByStorageId(int storageId);

	int getProductIdByStorageId(int storageId);

	ProductVO getProductByStorageId(int storageId);

}
