package com.java.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestContentDAO {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3307/testmanagement";
	static String uid = "root";
	static String pwd = "1234";
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private String query = null;
	
	public TestContentDAO() {
		try {
			Class.forName(driver);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<TestContentDTO> selectAllTestContent(String testId) {
		query = "select * from test_content where test_id=" + testId + " order by q_num";
		ArrayList<TestContentDTO> dtos = new ArrayList<TestContentDTO>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				int test_id = rs.getInt("test_id");
				int q_num = rs.getInt("q_num");
				int q_form = rs.getInt("q_form");
				String explanation = rs.getString("explanation");
				String answer1 = rs.getString("answer1");
				String answer2 = rs.getString("answer2");
				String answer3 = rs.getString("answer3");
				String answer4 = rs.getString("answer4");
				String answer = rs.getString("answer");
				String image = rs.getString("image");
				int score = rs.getInt("score");
				
				TestContentDTO dto = new TestContentDTO(test_id, q_num, q_form, explanation, answer1, answer2, answer3, answer4, answer, score, image);
				dtos.add(dto);
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
		return dtos; 
	}
	
	public void addTestContent(TestContentDTO dto) {
		query = "insert into test_content value(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, dto.getTest_id());
			pstmt.setInt(2, dto.getQ_num());
			pstmt.setInt(3, dto.getQ_form());
			pstmt.setString(4, dto.getExplanation());
			pstmt.setString(5, dto.getAnswer1());
			pstmt.setString(6, dto.getAnswer2());
			pstmt.setString(7, dto.getAnswer3());
			pstmt.setString(8, dto.getAnswer4());
			pstmt.setString(9, dto.getAnswer());
			pstmt.setInt(10, dto.getScore());
			pstmt.setString(11, dto.getImage());
			
			System.out.println(pstmt);
			
			int resultQuery = pstmt.executeUpdate();
			
			if(1 != resultQuery) System.out.println("???? ???? ????");
			else System.out.println("???? ???? ????");
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
	
	public void updateTestContent(ArrayList<TestContentDTO> dtos) {
		PreparedStatement pstmt = null;
		query = "update test_content "
				+ "set explanation = ?, q_form = ?, answer1 = ?, answer2 = ?, answer3 = ?, answer4 = ?, answer = ?, score = ? "
				+ "where test_id = ? AND q_num = ?";
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			
			for(int i = 0; i < dtos.size(); i++) {
				pstmt.setString(1, dtos.get(i).getExplanation());
				pstmt.setInt(2, dtos.get(i).getQ_form());
				pstmt.setString(3, dtos.get(i).getAnswer1());
				pstmt.setString(4, dtos.get(i).getAnswer2());
				pstmt.setString(5, dtos.get(i).getAnswer3());
				pstmt.setString(6, dtos.get(i).getAnswer4());
				pstmt.setString(7, dtos.get(i).getAnswer());
				pstmt.setInt(8, dtos.get(i).getScore());
				pstmt.setInt(9, dtos.get(i).getTest_id());
				pstmt.setInt(10, dtos.get(i).getQ_num());
				
				System.out.println(pstmt);
				
				int result = pstmt.executeUpdate();
				if(result == 1) 
					System.out.println("???? ????");
				else System.out.println("???? ????");
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
	}
	
	public void deleteTestContent(int test_id, int q_num) {
		query = "delete from test_content where test_id="+test_id+" AND q_num=" + q_num;
		String query2 = "update test_content set q_num = q_num-1 where test_id="+test_id+" AND q_num > " + q_num;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.executeUpdate(query2);
			System.out.println("???? ????");
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
	}
	
	public int getTotalScore(int test_id) {
		query = "select score from test_content where test_id = " + test_id;
		int totalScore = 0;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				 totalScore += rs.getInt("score");
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
		return totalScore;
	}
}

