package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.ItemStorageVO;
import com.poke.www.domain.PokemonStorageVO;
import com.poke.www.domain.ProductVO;

public interface StorageService {

	int addItem(String memberId, int productId);

	List<ItemStorageVO> getItemsByMemberId(String memberId);

	int removeItemByStorageId(int storageId);
	
	int removePokemonByStorageId(int storageId);

	int getProductIdByStorageId(int storageId);

	ProductVO getProductByStorageId(int storageId);
	
	PokemonStorageVO getPokemonByStorageId(int storageId);

	int addPokemon(String memberId,int pokemonId);

	List<PokemonStorageVO> getPokemonsByMemberId(String memberId);

}
