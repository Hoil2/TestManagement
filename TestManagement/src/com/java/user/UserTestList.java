package com.java.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.java.DB.MemberDTO;
import com.java.DB.TestDAO;
import com.java.DB.TestDTO;
import com.java.ex.Login;
import com.java.management.test.TestEditor;

public class UserTestList extends JFrame {
	
	Font font = new Font("돋움", Font.BOLD, 30);
	Font font2 = new Font("돋움", Font.BOLD, 20);
	int posX = 20, posY = 20;
	
	JPanel pane;
	JScrollPane scroll;
	
	public UserTestList(MemberDTO memberDTO) {
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,400);
		
		setTitle("시험 목록");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
		JButton btnUserInfoModify = new JButton("정보 수정");
		btnUserInfoModify.setBounds(680, 10, 100, 50);
		btnUserInfoModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserInfoModify(memberDTO);
			}
		});
		
		
		JButton btnTestRecord = new JButton("시험 기록");
		btnTestRecord.setBounds(800, 10, 100, 50);
		btnTestRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserTestRecord(memberDTO, memberDTO.getId());
			}
		});
		
		// 존재하는 시험 불러서 띄우기
		TestDAO dao = new TestDAO();
		ArrayList<TestDTO> dtos = new ArrayList<TestDTO>();
		
		dtos = dao.selectAllTest();
		
		for(int i = 0; i < dtos.size(); i++) {
			String testId = String.valueOf(dtos.get(i).getId());
			
			JLabel lbl = new JLabel(dtos.get(i).getName());
			lbl.setBounds(posX, posY + (i * 60), 300, 50);
			lbl.setFont(font);
			
			JButton btnTestStart = new JButton("시험 시작");
			btnTestStart.setBounds(posX + 650, posY + (i * 60), 150, 50);
			btnTestStart.setFont(font2);
			btnTestStart.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new Test(memberDTO, testId);
				}
			});
			
			pane.add(lbl);
			pane.add(btnTestStart);
			
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}
		// 시험 시작, 제출 구현하기
		// 시험 본 목록 구현하기
		add(btnUserInfoModify);
		add(btnBack);
		add(btnTestRecord);
		add(scroll);
		setVisible(true);
	}
}
