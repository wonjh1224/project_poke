package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.PokedexVO;
import com.poke.www.repository.PokedexMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokedexServiceImpl implements PokedexService {
	private final PokedexMapper mapper;

	@Override
	public int addPokemon(String memberId, int pokemonId) {
		return mapper.inputPokemon(memberId,pokemonId);
	}

	@Override
	public PokedexVO getPokemonByMemberIdAndPokemonId(String memberId, int pokemonId) {
		return mapper.selectPokemonByMemberIdAndPokemonId(memberId,pokemonId);
	}

	@Override
	public List<PokedexVO> getPokemonsByMemberId(String memberId) {
		return mapper.selectPokemonsByMemberId(memberId);
	}


}
