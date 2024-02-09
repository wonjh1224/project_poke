package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.ItemStorageVO;
import com.poke.www.domain.PokemonStorageVO;
import com.poke.www.domain.ProductVO;

@Mapper
public interface StorageMapper {

	int insertItem(String memberId, int productId);

	List<ItemStorageVO> selectItemsByMemberId(String memberId);

	int deleteItemByStorageId(int storageId);

	int selectProductIdByStorageId(int storageId);

	ProductVO selectProductByStorageId(int storageId);

	int insertPokemon(String memberId, int pokemonId);

	List<PokemonStorageVO> selectPokemonsByMemberId(String memberId);

	int deletePokemonByStorageId(int storageId);

}
