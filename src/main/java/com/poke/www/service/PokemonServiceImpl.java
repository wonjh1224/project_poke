package com.poke.www.service;

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
}
