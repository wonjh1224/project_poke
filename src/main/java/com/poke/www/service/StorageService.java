package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.ItemStorageVO;

public interface StorageService {

	int addItem(String memberId, int productId);

	List<ItemStorageVO> getItemsByMemberId(String memberId);

	int removeItemByStorageId(int storageId);

}
