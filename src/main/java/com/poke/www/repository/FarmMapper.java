package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.FarmVO;

@Mapper
public interface FarmMapper {

	void insertPokemon(String memberId, String string, String string2, String string3, String string4, String string5);

	FarmVO selectFarmList(String memberId);

}
