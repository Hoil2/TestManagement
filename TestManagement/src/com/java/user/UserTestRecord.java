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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.java.DB.MemberDTO;
import com.java.DB.TestContentDAO;
import com.java.DB.TestDAO;
import com.java.DB.TestRecordDAO;
import com.java.DB.TestRecordDTO;
import com.java.management.user.UserMng;

public class UserTestRecord extends JFrame{
	
	int posX = 20, posY = 20;
	int radioPosX = 0, radioPosY = 170;
	Font font25 = new Font("????", Font.PLAIN, 25);
	Font font20 = new Font("????", Font.PLAIN, 20);
	Font font15 = new Font("????", Font.PLAIN, 15);
	Font font20_bold = new Font("????", Font.BOLD, 20);
	JRadioButton radio[] = new JRadioButton[4];
	int selectedNum = 0;
	int nowPage = 0;
	
	TestRecordDAO dao = new TestRecordDAO();
	TestContentDAO testContentDAO = new TestContentDAO();
	MemberDTO memberDTO;
	MemberDTO managerDTO;
	TestDAO testDAO;
	ArrayList<TestRecordDTO> dtos;
	
	JPanel pane;
	JScrollPane scroll;
	
	public UserTestRecord(MemberDTO memberDTO, String member_id) {
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,400);
		
		this.memberDTO = memberDTO;
		
		TestRecordDAO dao = new TestRecordDAO();
		testDAO = new TestDAO();
		dtos = dao.selectTestRecord(member_id);
		
		setTitle("???? ????");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null); // ȭ?? ?߾? ??ġ
		setResizable(false);
		
		JButton btnBack = new JButton("?ڷΰ???");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				if(memberDTO.getPosition().equals("manager"))
					new UserMng(memberDTO);
				else
					new UserTestList(memberDTO);
			}
		});
		
		JLabel _lblTestName = new JLabel("??????");
		_lblTestName.setBounds(posX+150, posY, 300, 50);
		_lblTestName.setFont(font20_bold);
		
		JLabel _lblSubmitDateTime = new JLabel("???? ??¥,?ð?");
		_lblSubmitDateTime.setBounds(posX+300, posY, 300, 50);
		_lblSubmitDateTime.setFont(font20_bold);
		
		JLabel _lblScore = new JLabel("????");
		_lblScore.setBounds(posX+550, posY, 300, 50);
		_lblScore.setFont(font20_bold);
		
		for(int i = 0; i < dtos.size(); i++) {
			int _i = i;
			int totalScore = testContentDAO.getTotalScore(dtos.get(i).getTest_id());
			String testName = testDAO.testIdToTestName(dtos.get(i).getTest_id());
			JLabel lblTestName = new JLabel(testName);
			lblTestName.setBounds(posX+70, posY+ (i * 60), 300, 50);
			lblTestName.setFont(font20);
			
			JLabel lblSubmitDateTime = new JLabel(dtos.get(i).getSubmit_datetime().toString());
			lblSubmitDateTime.setBounds(posX+220, posY + (i * 60), 300, 50);
			lblSubmitDateTime.setFont(font20);
			
			JLabel lblScore = new JLabel(dtos.get(i).getScore() + "/" + totalScore);
			lblScore.setBounds(posX+470, posY + (i * 60), 300, 50);
			lblScore.setFont(font20);
			
			JButton btnTestRecordOpen = new JButton("?????? ????");
			btnTestRecordOpen.setBounds(posX + 650, posY + (i * 60), 150, 50);
			btnTestRecordOpen.setFont(font20);
			btnTestRecordOpen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new UserTestReview(memberDTO, member_id, dtos.get(_i).getTest_id(), dtos.get(_i).getTest_num());
				}
			});
			
			pane.add(lblTestName);
			pane.add(lblSubmitDateTime);
			pane.add(lblScore);
			pane.add(btnTestRecordOpen);
			
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}
		add(btnBack);
		add(_lblTestName);
		add(_lblSubmitDateTime);
		add(_lblScore);
		add(scroll);
		setVisible(true);
	}
}
