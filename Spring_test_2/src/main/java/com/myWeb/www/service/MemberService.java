package com.myWeb.www.service;

import java.util.List;

import com.myWeb.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	int modify(MemberVO mvo);

	String getPassword(MemberVO mvo);

	List<MemberVO> getMemList();

}
