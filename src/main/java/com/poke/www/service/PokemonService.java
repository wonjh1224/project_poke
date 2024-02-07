package com.poke.www.service;

import com.poke.www.domain.PokemonVO;

public interface PokemonService {

	void cleanTable();

	int addPokemon(PokemonVO pvo);

}
