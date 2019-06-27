package com.uek.model;

import java.io.Serializable;

public class Member implements Serializable {
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", nickname=" + nickname + ", signature=" + signature
				+ ", password=" + password + ", header=" + header + "]";
	}
	private int id;
	private String name;
	private String nickname;
	private String signature;
	private String password;
	private String header;
	
	
	public Member() {
		super();
	}
	public Member(String name, String nickname, String signature, String password, String header) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.signature = signature;
		this.password = password;
		this.header = header;
	}
	public Member(int id, String name, String nickname, String signature, String password, String header) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.signature = signature;
		this.password = password;
		this.header = header;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
}
