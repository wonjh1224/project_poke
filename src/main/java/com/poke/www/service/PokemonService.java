package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.PokemonVO;

public interface PokemonService {

	void cleanTable();

	int addPokemon(PokemonVO pvo);

	List<PokemonVO> getPokemons();

	PokemonVO getPokemonByPokemonId(int pokemonId);

}
