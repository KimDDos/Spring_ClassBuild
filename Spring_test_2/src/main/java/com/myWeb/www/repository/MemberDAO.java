package com.myWeb.www.repository;

import java.util.List;

import com.myWeb.www.security.AuthVO;
import com.myWeb.www.security.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	MemberVO selectEmail(String email);

	int insertAuthInit(String email);

	List<AuthVO> selectAuths(String email);

	int updateLastLogin(String authEmail);

}
