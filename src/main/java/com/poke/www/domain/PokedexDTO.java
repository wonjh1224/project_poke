package com.poke.www.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PokedexDTO {
	private List<PokedexVO> userPokemons;
	private List<PokemonVO> allPokemons;
}
