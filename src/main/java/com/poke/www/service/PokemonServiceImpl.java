package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.PokemonVO;
import com.poke.www.repository.PokemonMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService{
	private final PokemonMapper mapper;

	@Override
	public void cleanTable() {
		mapper.deleteAll();
	}

	@Override
	public int addPokemon(PokemonVO pvo) {
		return mapper.insertPokemon(pvo);
	}

	@Override
	public List<PokemonVO> getPokemons() {
		return mapper.selectPokemons();
	}

	@Override
	public PokemonVO getPokemonByPokemonId(int pokemonId) {
		return mapper.selectPokemonByPokemonId(pokemonId);
	}

	@Override
	public PokemonVO getPokemonByName(int pokemonId) {
		// TODO Auto-generated method stub
		return mapper.selectPokemonByName(pokemonId);
	}

	@Override
	public String[] getPokemonImage(int slot1, int slot2, int slot3, int slot4, int slot5) {
		// TODO Auto-generated method stub
		return mapper.selectPokemonImage(slot1, slot2, slot3, slot4, slot5);
	}

	@Override
	public int getTotalPoint(String string, String string2, String string3, String string4, String string5) {
		// TODO Auto-generated method stub
		return mapper.selectTotalPoint(string, string2, string3, string4, string5);
	}


}
