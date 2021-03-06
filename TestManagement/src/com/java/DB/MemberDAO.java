package com.java.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3307/testmanagement";
	static String uid = "root";
	static String pwd = "1234";
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private String query = null;
	
	public MemberDAO() {
		try {
			Class.forName(driver);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberDTO selectMember(String id) {
		query = "select * from member where m_id='"+id+"'";
		MemberDTO dto = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			rs.next();
			
			String pw = rs.getString("m_pw");
			String email = rs.getString("m_email");
			String name = rs.getString("m_name");
			String position = rs.getString("m_position");
			Date signupDate = rs.getDate("m_signup_date");
			
			dto = new MemberDTO(id, pw, name, email, position, signupDate);
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
		return dto;
	}
	
	public ArrayList<MemberDTO> selectAllMember() {
		query = "select * from member";
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				String id = rs.getString("m_id");
				String pw = rs.getString("m_pw");
				String email = rs.getString("m_email");
				String name = rs.getString("m_name");
				String position = rs.getString("m_position");
				Date signupDate = rs.getDate("m_signup_date");
				
				MemberDTO dto = new MemberDTO(id, pw, name, email, position, signupDate);
				dtos.add(dto);
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
	
	
	
	public void mDelete(MemberDTO dto) {
		query = "delete from member where id='" + dto.getId() + "'";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			int result = stmt.executeUpdate(query);
			if(result == 1) 
				System.out.println("???? ????");
			else System.out.println("???? ????");
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
	}
	
	public void signUpMember(MemberDTO dto) {
		query = "insert into member value(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPosition());
			pstmt.setDate(6, dto.getSignupDate());
			
			pstmt.executeUpdate();
			 
			System.out.println("ȸ?????? ????");
			
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
	
	public void updateMember(MemberDTO dto) {
		query = "update member set m_pw = ?, m_name = ?, m_email = ? where m_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getId());
			
			pstmt.executeUpdate();
			 
			System.out.println("ȸ?? ???? ???? ????");
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
}