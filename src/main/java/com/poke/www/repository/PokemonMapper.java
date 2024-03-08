package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.PokemonVO;

@Mapper
public interface PokemonMapper {

	void deleteAll();

	int insertPokemon(PokemonVO pvo);

	List<PokemonVO> selectPokemons();

	PokemonVO selectPokemonByPokemonId(int pokemonId);

	PokemonVO selectPokemonByName(int pokemonId);

	String[] selectPokemonImage(int slot1, int slot2, int slot3, int slot4, int slot5);

	int selectTotalPoint(String string, String string2, String string3, String string4, String string5);

}
