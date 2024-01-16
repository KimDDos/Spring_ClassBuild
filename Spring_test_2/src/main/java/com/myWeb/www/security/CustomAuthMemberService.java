package com.myWeb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myWeb.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

public class CustomAuthMemberService implements UserDetailsService {

	@Inject
	private MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username이 DB에 설정되어있는 email가 맞는지 check
		// 인증하여 해당 객체를 AuthMember로 리턴
		MemberVO mvo = mdao.selectEmail(username);
		if(mvo == null) { // 해당 유저가 등록되지 않은 유저
			throw new UsernameNotFoundException(username);
		}
		mvo.setAuthList(mdao.selectAuths(username));
		return new AuthMember(mvo);
	}

}
