package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class TradeVO {
	private int tradeId;
	private String buyer;
	private String seller;
	private int price;
	private int pokemonId;
	private String tradeDate;
	
	private String name;
}
