package com.java.DB;

public class TestContentDTO {
	private int test_id;
	private int q_num;
	private int q_form;
	private String explanation;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer;
	private int score;
	private String image;
	
	public TestContentDTO() {
		
	}
	
	public TestContentDTO(int test_id, int q_num, int q_form, String explanation, String answer1, String answer2,
			String answer3, String answer4, String answer, int score, String image) {
		this.test_id = test_id;
		this.q_num = q_num;
		this.q_form = q_form;
		this.explanation = explanation;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer = answer;
		this.score = score;
		this.image = image;
	}

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	public int getQ_num() {
		return q_num;
	}

	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}

	public int getQ_form() {
		return q_form;
	}

	public void setQ_form(int q_form) {
		this.q_form = q_form;
	}
	
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}

