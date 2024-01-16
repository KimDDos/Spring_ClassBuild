package com.myWeb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myWeb.www.repository.MemberDAO;
import com.myWeb.www.security.MemberVO;


@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO mdao;
	

	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.insert(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
	}


	@Override
	public boolean updateLastLogin(String authEmail) {
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}


	@Override
	public int modify(MemberVO mvo) {
		return mdao.modify(mvo);
	}


	@Override
	public String getPassword(MemberVO mvo) {
		return mdao.getPwd(mvo.getEmail());
	}


	@Override
	public List<MemberVO> getMemList() {
		List<MemberVO> mlist = mdao.getList();
		for(MemberVO m : mlist) {
			m.setAuthList(mdao.selectAuths(m.getEmail()));
		}
		return mlist;
	}
	
}
