package com.java.management;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.java.DB.MemberDTO;
import com.java.ex.Login;
import com.java.management.test.TestMng;
import com.java.management.user.UserMng;

public class Management extends JFrame{
	JButton btnUserMng, btnTestMng, btnLogout;
	Font font = new Font("돋움", Font.BOLD, 30);
	MemberDTO managerDTO;
	public Management(MemberDTO managerDTO) {
		this.managerDTO = managerDTO;
		setTitle("관리자");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		int posX = 255, posY = 200;
		
		btnUserMng = new JButton("회원 관리");
		btnUserMng.setBounds(posX, posY, 200, 150);
		btnUserMng.setFont(font);
		btnUserMng.addActionListener(new MoveUserMngPage());
		
		btnTestMng = new JButton("시험 관리");
		btnTestMng.setBounds(posX + 300, posY, 200, 150);
		btnTestMng.setFont(font);
		btnTestMng.addActionListener(new MoveTestMngPage());
		
		JButton btnLogout = new JButton("로그아웃");
		btnLogout.setBounds(10, 10, 100, 50);
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
		add(btnUserMng);
		add(btnTestMng);
		add(btnLogout);
		
		setVisible(true);
	}
	
	class MoveUserMngPage implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new UserMng(managerDTO);
		}
	}
	
	class MoveTestMngPage implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new TestMng(managerDTO);
		}
	}
}
