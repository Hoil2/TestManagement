package com.java.user;

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
import com.java.ex.Login;

public class UserInfoModify extends JFrame {
int posX = 50, posY = 50;
	
	JTextField txtName;
	JTextField txtID;
	JPasswordField txtPW;
	JTextField txtEmail;
	
	public UserInfoModify(MemberDTO memberDTO) {
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
		txtName.setText(memberDTO.getName());
		
		JLabel lblID = new JLabel("아이디");
		lblID.setBounds(posX, posY + 70, 100, 40);
		
		txtID = new JTextField();
		txtID.setBounds(posX+100, posY + 70, 200, 40);
		txtID.setText(memberDTO.getId());
		txtID.setEditable(false);
		
		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setBounds(posX, posY + 140, 100, 40);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(posX+100, posY + 140, 200, 40);
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setBounds(posX, posY + 210, 100, 40);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(posX+100, posY + 210, 200, 40);
		txtEmail.setText(memberDTO.getEmail());
		
		JButton btnSignUp = new JButton("정보 수정");
		btnSignUp.setBounds(80, 380, 100, 50);
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isBlanks()) {
					JOptionPane.showMessageDialog(null, "빈 칸이 있습니다.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				MemberDAO dao = new MemberDAO();
				
				MemberDTO dto = dao.selectMember(txtID.getText());
				dto.setPw(txtPW.getText());
				dto.setName(txtName.getText());
				dto.setEmail(txtEmail.getText());
				
				dao.updateMember(dto);
				setVisible(false);
				new Login();
				
				JOptionPane.showMessageDialog(null, "정보 수정을 완료했습니다.", "Success", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(210, 380, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserTestList(memberDTO);
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
