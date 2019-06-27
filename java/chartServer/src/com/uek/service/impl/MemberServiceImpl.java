package com.uek.service.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import com.uek.dao.impl.MemberDaoImpl;
import com.uek.dao.inter.IMemberDao;
import com.uek.model.Member;
import com.uek.service.inter.IMemberService;

public class MemberServiceImpl implements IMemberService{
	
	//调用dao层的方法
	IMemberDao memberDao  = new MemberDaoImpl();
	@Override
	public void add(Member member) {
		// TODO Auto-generated method stub
		memberDao.add(member);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Member load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member loadByName(String name) {
		
		return memberDao.loadByName(name);
	}

	
	//登录验证
	@Override
	public Member login(String name, String password) {
		// TODO Auto-generated method stub
		Member member =  memberDao.loadByName(name);
		if(member == null) {
			throw new RuntimeErrorException(null, "用户不存在！");
		}
		if(!password.equals(member.getPassword())){
			throw new RuntimeErrorException(null, "密码错误！");
		}
		return member;
	}

	@Override
	public List<Member> loadFriends(int selfId) {
		memberDao.loadFriends(selfId);
		return null;
	}
	
}
