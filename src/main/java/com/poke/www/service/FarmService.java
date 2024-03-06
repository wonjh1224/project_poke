package com.poke.www.service;

import com.poke.www.domain.FarmVO;

public interface FarmService {

	void addPokemon(String memberId, String string, String string2, String string3, String string4, String string5);

	FarmVO getFarmList(String memberId);

	void delPokemon(String memberId);

	String getEndDate(String memberId);

}
