package com.poke.www.service;

import org.springframework.stereotype.Service;

import com.poke.www.repository.MyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyServiceImpl implements MyService {
	private final MyMapper mapper;
}
