package com.uek.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.uek.dao.inter.IMemberDao;
import com.uek.model.Member;
import com.uek.utils.DBUtils;

public class MemberDaoImpl implements IMemberDao{
	
	//注册用户
	@Override
	public void add(Member member) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = DBUtils.getConnection();
			stat = conn.createStatement();
			String sql = "insert into t_member(name,nickname,signature,password,header) "
					+ "values('"+member.getName()+"','"+member.getNickname()+"','"+member.getSignature()+"','"+member.getPassword()+"','"+member.getHeader()+"')";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.freeResource(conn, stat);
		}	
		
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

	
	//根据账号查询用户
	@Override
	public Member loadByName(String name) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		Member member = null;
		try {
			conn = DBUtils.getConnection();
			stat = conn.createStatement();
			String sql = "select * from t_member where name='"+name+"'";
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setNickname(rs.getString("nickname"));
				member.setSignature(rs.getString("signature"));
				member.setPassword(rs.getString("password"));
				member.setHeader(rs.getString("header"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.freeResource(conn, stat, rs);
		}
		
		return member;
	}

	@Override
	public void loadFriends(int id) {
		// TODO Auto-generated method stub
		
	}


}
