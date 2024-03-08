package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.MemberVO;
import com.poke.www.repository.MemberMapper;
import com.poke.www.repository.RankingMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper mapper;

	@Override
	public int register(MemberVO mvo) {
		return mapper.insertMember(mvo);
	}

	@Override
	public MemberVO login(String loginId, String password) {
		MemberVO mvo = mapper.selectMemberById(loginId);
		if (mvo != null && mvo.getPassword().equals(password)) {
			return mvo;
		}
		return null;

	}

	@Override
	public MemberVO getMember(String memberId) {
		return mapper.selectMemberById(memberId);
	}

	@Override
	public int modifyPointByMemberId(String memberId, int price) {
		return mapper.updatePointById(price, memberId);
	}

	@Override
	public List<MemberVO> getRanking() {
		return mapper.selectMembersOrderByScore();
	}

	@Override
	public int addScore(int score, String memberId) {
		return mapper.updateScoreByMemberId(score, memberId);
	}

	@Override
	public int modifyHasProfile(String memberId, boolean b) {
		String status = b ? "y" : "n";
		return mapper.updateHasProfile(status, memberId);
	}

	@Override
	public MemberVO getMemberByNickname(String nickname) {
		return mapper.selectMemberByNickname(nickname);
	}

	@Override
	public int addProfile(String memberId, String uuid) {
		return mapper.insertProfile(memberId, uuid);
	}

	@Override
	public String getProfileUuidByMemberId(String memberId) {
		return mapper.selectProfileUuidByMemberId(memberId);
	}

	@Override
	public void updatePoint(String memberId, int point) {
		mapper.updatePoint(memberId, point);

	}

}
