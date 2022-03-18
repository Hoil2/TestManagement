package com.java.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestDAO {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3307/testmanagement";
	static String uid = "root";
	static String pwd = "1234";
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private String query = null;
	
	public TestDAO() {
		try {
			Class.forName(driver);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<TestDTO> selectAllTest() {
		query = "select * from test";
		ArrayList<TestDTO> dtos = new ArrayList<TestDTO>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				int test_id = rs.getInt("test_id");
				String test_name = rs.getString("test_name");
				String test_madeby = rs.getString("test_madeby");
				
				TestDTO dto = new TestDTO(test_id, test_name, test_madeby);
				dtos.add(dto);
			}
		} catch(SQLException ex) {
			System.out.println("���� ����");
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
	
	public void createTest(TestDTO dto) {
		query = "insert into test (test_name, test_madeby) value(?, ?)";
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getMadeby());
			pstmt.executeUpdate();
			
			System.out.println(pstmt);System.out.println("���� ��� ����");
		} catch(SQLException ex) {
			System.out.println("���� ����");
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
	
	public void deleteTest(String id) {
		query = "delete from test where test_id=?"; // test_content�� test_id�� ON DELETE CASCADE�� �����ؼ� �ѹ��� ������
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.valueOf(id));
			pstmt.executeUpdate();
			System.out.println("���� ����");
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
	
	public String testIdToTestName(int test_id) {
		String testName = "";
		query = "select test_name from test where test_id = "+test_id;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			rs.next();
			
			testName = rs.getString("test_name");
			
		} catch(SQLException ex) {
			System.out.println("���� ����");
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return testName;
	}
}