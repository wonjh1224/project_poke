package com.poke.www.service;

import org.springframework.stereotype.Service;

import com.poke.www.repository.PokedexMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokedexServiceImpl implements PokedexService {
	private final PokedexMapper mapper;


}
