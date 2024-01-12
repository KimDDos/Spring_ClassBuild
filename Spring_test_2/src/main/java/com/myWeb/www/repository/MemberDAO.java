package com.myWeb.www.repository;

import com.myWeb.www.security.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	int selectEmail();

}
