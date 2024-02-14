package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.PokedexVO;

@Mapper
public interface PokedexMapper {

	int inputPokemon(String memberId, int pokemonId);

	PokedexVO selectPokemonByMemberIdAndPokemonId(String memberId, int pokemonId);

	List<PokedexVO> selectPokemonsByMemberId(String memberId);



}
