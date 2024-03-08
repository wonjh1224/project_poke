package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class FarmVO {

	private String member_id;
	private int slot1, slot2, slot3, slot4, slot5;
	private String regDate, endDate;
	private int totalPoint;
	
}
