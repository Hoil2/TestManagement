package com.java.DB;

import java.sql.Date;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String position;
	private Date signupDate;
	
	public MemberDTO() {
		id = "";
		pw = "";
		name = "";
		email = "";
		position = "";
		signupDate = null;
	}
	
	
	public MemberDTO(String id, String pw, String name, String email, String position, Date signupDate) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.position = position;
		this.signupDate = signupDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public Date getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}

}