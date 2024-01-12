package com.myWeb.www.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myWeb.www.repository.MemberDAO;
import com.myWeb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberDAO mdao;

	@Override
	public int register(MemberVO mvo) {
		BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
		int result = mdao.selectEmail();
		if(result > 0) {
			mvo.setPwd(PasswordEncoder.encode(mvo.getPwd()));
		} else {
			return 0;
		}
		return mdao.insert(mvo);
	}
	
}
