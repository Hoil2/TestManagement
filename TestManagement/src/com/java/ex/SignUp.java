package com.java.ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.java.DB.MemberDAO;
import com.java.DB.MemberDTO;

public class SignUp extends JFrame {
	int posX = 50, posY = 50;
	
	JTextField txtName;
	JTextField txtID;
	JPasswordField txtPW;
	JTextField txtEmail;
	
	SignUp() {
		setTitle("회원 가입");
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		JLabel lblName = new JLabel("이름");
		lblName.setBounds(posX, posY, 100, 40);
		
		txtName = new JTextField();
		txtName.setBounds(posX+100, posY, 200, 40);
		
		JLabel lblID = new JLabel("아이디");
		lblID.setBounds(posX, posY + 70, 100, 40);
		
		txtID = new JTextField();
		txtID.setBounds(posX+100, posY + 70, 200, 40);
		
		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setBounds(posX, posY + 140, 100, 40);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(posX+100, posY + 140, 200, 40);
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setBounds(posX, posY + 210, 100, 40);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(posX+100, posY + 210, 200, 40);
		
		JButton btnSignUp = new JButton("회원가입");
		btnSignUp.setBounds(80, 380, 100, 50);
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isBlanks()) {
					JOptionPane.showMessageDialog(null, "빈 칸이 있습니다.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				MemberDAO dao = new MemberDAO();
				if(dao.selectMember(txtID.getText()) != null ) {
					JOptionPane.showMessageDialog(null, "존재하는 아이디입니다.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Date date = new Date(new java.util.Date().getTime()); // 현재 날짜 가져오기
				MemberDTO dto = new MemberDTO(txtID.getText(), txtPW.getText(), txtName.getText(), txtEmail.getText(), "user", date);
				dao.signUpMember(dto);
				setVisible(false);
				new Login();
				
				JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.", "Success", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(210, 380, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
		add(lblName);
		add(txtName);
		add(lblID);
		add(txtID);
		add(lblPW);
		add(txtPW);
		add(lblEmail);
		add(txtEmail);
		add(btnSignUp);
		add(btnBack);
		
		setVisible(true);
	}
	
	boolean isBlanks() {
		if(txtID.getText().equals("") || txtPW.getText().equals("") || txtName.getText().equals("") || txtEmail.getText().equals("")) {
			return true;
		}
		return false;
	}

}
