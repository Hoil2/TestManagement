package com.java.DB;

public class TestSubmittedAnswersDTO {
	private int test_num;
	private int q_num;
	private String user_answer;
	
	public TestSubmittedAnswersDTO(int test_num, int q_num, String user_answer) {
		this.test_num = test_num;
		this.q_num = q_num;
		this.user_answer = user_answer;
	}
	
	public int getTest_num() {
		return test_num;
	}
	public void setTest_num(int test_num) {
		this.test_num = test_num;
	}
	public int getQ_num() {
		return q_num;
	}
	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}
	public String getUser_answer() {
		return user_answer;
	}
	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}
}
