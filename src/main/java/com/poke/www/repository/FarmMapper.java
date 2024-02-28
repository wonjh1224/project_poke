package com.poke.www.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FarmMapper {

	void insertPokemon(String memberId, String string, String string2, String string3, String string4, String string5);

}
