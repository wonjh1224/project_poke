package com.poke.www.repository;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.PokedexVO;

@Mapper
public interface PokedexMapper {

	int inputPokemon(String memberId, int pokemonId);

	PokedexVO selectPokemonByMemberIdAndPokemonId(String memberId, int pokemonId);



}
