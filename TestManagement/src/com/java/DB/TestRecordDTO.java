package com.java.DB;

import java.util.Date;

public class TestRecordDTO {
	private int test_num;
	private int test_id;
	private String m_id;
	private Object submit_datetime;
	private int score;
	
	public TestRecordDTO() {
		
	}
	
	public TestRecordDTO(int test_num, int test_id, String m_id, Object submit_datetime, int score) {
		this.test_num = test_num;
		this.test_id = test_id;
		this.m_id = m_id;
		this.submit_datetime = submit_datetime;
		this.score = score;
	}
	
	public int getTest_num() {
		return test_num;
	}
	public void setTest_num(int test_num) {
		this.test_num = test_num;
	}
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public Object getSubmit_datetime() {
		return submit_datetime;
	}
	public void setSubmit_datetime(Object submit_datetime) {
		this.submit_datetime = submit_datetime;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
