package com.poke.www.repository;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.PokemonVO;

@Mapper
public interface PokemonMapper {

	void deleteAll();

	int insertPokemon(PokemonVO pvo);

}
