package com.uek.dao.inter;

import java.util.List;

import com.uek.model.Member;

public interface IMemberDao {
	void add(Member member);
	void delete(int id);
	void update(Member member);
	Member load(int id);
	List<Member>list();
	
	Member loadByName(String name); //根据用户名查找
	void loadFriends(int id);
}
