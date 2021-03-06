package com.java.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestRecordDAO {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3307/testmanagement";
	static String uid = "root";
	static String pwd = "1234";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	Statement stmt = null; 
	ResultSet rs = null;
	
	private String query = null;
	
	public TestRecordDAO() {
		try {
			Class.forName(driver);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<TestRecordDTO> selectTestRecord(String m_id) {
		query = "select * from test_record where m_id='"+m_id+"' order by submit_datetime DESC";
		ArrayList<TestRecordDTO> dtos = new ArrayList<TestRecordDTO>();
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int test_num = rs.getInt("test_num");
				int test_id = rs.getInt("test_id");
				Object datetime = rs.getObject("submit_datetime");
				int score = rs.getInt("score");
				
				boolean contain = false;
				for(TestRecordDTO dto : dtos) {
					if(dto.getTest_id() == test_id) {
						contain = true;
						break;
					}
				}
				if(contain) continue;
				
				dtos.add(new TestRecordDTO(test_num, test_id, m_id, datetime, score));
			}
		} catch(SQLException ex) {
			System.out.println("???? ????");
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
	
	public ArrayList<TestRecordDTO> selectUserTestRecord(String m_id) {
		query = "select * "
				+ "from("
					+ "select * "
					+ "from test_record "
					+ "where (test_id, m_id, submit_datetime) in ("
						+ "select test_id, m_id, max(submit_datetime) "
						+ "from test_record group by test_id, m_id"
					+ ") "
					+ "order by submit_datetime desc"
				+ ")t "
				+ "where m_id = '+"+m_id+"' "
				+ "group by t.test_id, t.m_id";
		
		ArrayList<TestRecordDTO> dtos = new ArrayList<TestRecordDTO>();
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int test_num = rs.getInt("test_num");
				int test_id = rs.getInt("test_id");
				Object datetime = rs.getObject("submit_datetime");
				int score = rs.getInt("score");
				
				dtos.add(new TestRecordDTO(test_num, test_id, m_id, datetime, score));
			}
			System.out.println("???? ?ε? ????");
		} catch(SQLException ex) {
			System.out.println("???? ????");
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
	
	public void submitTest(TestRecordDTO dto) {
		query = "insert into test_record(test_id, m_id, submit_datetime, score) value(?,?,?,?)";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, dto.getTest_id());
			pstmt.setString(2, dto.getM_id());
			pstmt.setObject(3, dto.getSubmit_datetime());
			pstmt.setInt(4, dto.getScore());
			
			pstmt.executeUpdate();
			System.out.println("?????? ???? ????");
		} catch(SQLException ex) {
			System.out.println("???? ????");
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
	
	public int returnLastTestNum() {
		query = "select test_num from test_record order by test_num DESC LIMIT 1";
		int result = -1;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			result = rs.getInt("test_num");
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
		
		return result;
	}
}
