package com.java.DB;

public class TestDTO {
	private int id;
	private String name;
	private String madeby;
	
	public TestDTO() {
		
	}
	
	public TestDTO(int id, String name, String madeby) {
		this.id = id;
		this.name = name;
		this.madeby = madeby;
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
	public String getMadeby() {
		return madeby;
	}
	public void setMadeby(String madeby) {
		this.madeby = madeby;
	}
	
	
}
