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
		setTitle("ȸ�� ����");
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // ���� ��ġ
		setLocationRelativeTo(null); // ȭ�� �߾� ��ġ
		setResizable(false);
		
		JLabel lblName = new JLabel("�̸�");
		lblName.setBounds(posX, posY, 100, 40);
		
		txtName = new JTextField();
		txtName.setBounds(posX+100, posY, 200, 40);
		
		JLabel lblID = new JLabel("���̵�");
		lblID.setBounds(posX, posY + 70, 100, 40);
		
		txtID = new JTextField();
		txtID.setBounds(posX+100, posY + 70, 200, 40);
		
		JLabel lblPW = new JLabel("��й�ȣ");
		lblPW.setBounds(posX, posY + 140, 100, 40);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(posX+100, posY + 140, 200, 40);
		
		JLabel lblEmail = new JLabel("�̸���");
		lblEmail.setBounds(posX, posY + 210, 100, 40);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(posX+100, posY + 210, 200, 40);
		
		JButton btnSignUp = new JButton("ȸ������");
		btnSignUp.setBounds(80, 380, 100, 50);
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isBlanks()) {
					JOptionPane.showMessageDialog(null, "�� ĭ�� �ֽ��ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				MemberDAO dao = new MemberDAO();
				if(dao.selectMember(txtID.getText()) != null ) {
					JOptionPane.showMessageDialog(null, "�����ϴ� ���̵��Դϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Date date = new Date(new java.util.Date().getTime()); // ���� ��¥ ��������
				MemberDTO dto = new MemberDTO(txtID.getText(), txtPW.getText(), txtName.getText(), txtEmail.getText(), "user", date);
				dao.signUpMember(dto);
				setVisible(false);
				new Login();
				
				JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����߽��ϴ�.", "Success", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		JButton btnBack = new JButton("�ڷΰ���");
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