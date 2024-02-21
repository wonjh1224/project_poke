package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class RankingVO {
	
	private String memberId;
	private int ranking;
	
	private int score;
	
}
