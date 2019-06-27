package com.uek.service.inter;

import java.util.List;

import com.uek.model.Member;

public interface IMemberService {
	void add(Member member);
	void delete(int id);
	void update(Member member);
	Member load(int id);
	List<Member>list();
	
	Member loadByName(String name); //根据用户名查找
	Member login(String name,String password);//用户名密码验证
	List<Member> loadFriends(int selfId);
}
