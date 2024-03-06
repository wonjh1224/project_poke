package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.FarmVO;
import com.poke.www.repository.FarmMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FarmServiceImpl implements FarmService{

	private final FarmMapper farmMapper;

	@Override
	public void addPokemon(String memberId, String string, String string2, String string3, String string4, String string5) {
		farmMapper.insertPokemon(memberId, string, string2, string3, string4, string5);
		
	}

	@Override
	public FarmVO getFarmList(String memberId) {
		// TODO Auto-generated method stub
		return farmMapper.selectFarmList(memberId);
	}

	@Override
	public void delPokemon(String memberId) {
		farmMapper.deletePokemon(memberId);
		
	}

	@Override
	public String getEndDate(String memberId) {
		// TODO Auto-generated method stub
		return farmMapper.selectEndDate(memberId);
	}

	
}
