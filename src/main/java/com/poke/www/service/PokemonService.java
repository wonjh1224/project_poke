package com.poke.www.service;

import java.util.List;

import com.poke.www.domain.PokemonVO;

public interface PokemonService {

	void cleanTable();

	int addPokemon(PokemonVO pvo);

	List<PokemonVO> getPokemons();

	PokemonVO getPokemonByPokemonId(int pokemonId);

	PokemonVO getPokemonByName(int pokemonId);

	String[] getPokemonImage(int slot1, int slot2, int slot3, int slot4, int slot5);

}
