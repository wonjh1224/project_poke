package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.PokedexVO;

public interface PokedexService {

	int addPokemon(String memberId, int pokemonId);

	PokedexVO getPokemonByMemberIdAndPokemonId(String memberId, int pokemonId);

	List<PokedexVO> getPokemonsByMemberId(String memberId);

	List<PokedexVO> getPokemonsByMemberIdOderByScore(String memberId);



}
