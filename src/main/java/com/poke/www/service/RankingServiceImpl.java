package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.MemberVO;
import com.poke.www.repository.RankingMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{
	private final RankingMapper mapper;

}
