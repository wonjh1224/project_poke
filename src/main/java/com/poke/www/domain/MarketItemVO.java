package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class MarketItemVO {
	private int itemId;
	private int pokemonId;  
	private String memberId;
	private int price;
	private String start_date;
	private String end_date;
}
