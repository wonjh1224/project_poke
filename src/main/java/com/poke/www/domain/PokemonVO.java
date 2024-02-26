package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString 
@AllArgsConstructor @NoArgsConstructor
public class PokemonVO {
	private int pokemonId;
	private String name;
	private String image;
	private String flavor;
	private int score;
	private int height;
	private int weight;
	private String genus;
	private String type;
}
