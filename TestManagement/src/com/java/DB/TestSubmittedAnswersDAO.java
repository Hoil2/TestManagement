package com.java.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestSubmittedAnswersDAO {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3307/testmanagement";
	static String uid = "root";
	static String pwd = "1234";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	Statement stmt = null; 
	ResultSet rs = null;
	
	private String query = null;
	
	public TestSubmittedAnswersDAO() {
		try {
			Class.forName(driver);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertAnswers(ArrayList<TestSubmittedAnswersDTO> dtos) {
		query = "insert into test_submitted_answers values (?,?,?)";
		TestRecordDAO dao = new TestRecordDAO();
		int test_num = dao.returnLastTestNum();
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			for(int i = 0; i < dtos.size(); i++) {
				
				pstmt = con.prepareStatement(query);
				
				pstmt.setInt(1, test_num);
				pstmt.setInt(2, dtos.get(i).getQ_num());
				pstmt.setString(3, dtos.get(i).getUser_answer());
				System.out.println(pstmt);
				
				pstmt.executeUpdate();
				
			}
			System.out.println("insert user answer");
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> selectUserAnswers(int test_num) {
		query = "select user_answer from test_submitted_answers where test_num = " + test_num + " order by q_num";
		ArrayList<String> answers = new ArrayList<String>();
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String answer = rs.getString("user_answer");
				answers.add(answer);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return answers;
	}
}
