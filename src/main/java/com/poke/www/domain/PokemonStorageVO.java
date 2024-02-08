package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString 
@AllArgsConstructor @NoArgsConstructor
public class PokemonStorageVO {
	private int storageId;
	private String memberId;
	private int pokemonId;
	
	private String name;
	private String image;
}
