package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.PokemonVO;

@Mapper
public interface PokemonMapper {

	void deleteAll();

	int insertPokemon(PokemonVO pvo);

	List<PokemonVO> selectPokemons();

}
