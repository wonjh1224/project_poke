package com.poke.www.service;

import org.springframework.stereotype.Service;

import com.poke.www.repository.StorageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService{
	private final StorageMapper mapper;

	@Override
	public int addItem(String memberId, int productId) {
		
		return mapper.insertItem(memberId,productId);
	}
}
