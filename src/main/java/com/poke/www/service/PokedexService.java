package com.poke.www.service;

import com.poke.www.domain.PokedexVO;

public interface PokedexService {

	int addPokemon(String memberId, int pokemonId);

	PokedexVO getPokemonByMemberIdAndPokemonId(String memberId, int pokemonId);



}
