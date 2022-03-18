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
	
	Font font = new Font("����", Font.BOLD, 30);
	Font font2 = new Font("����", Font.BOLD, 20);
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
		
		setTitle("���� ���");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // ���� ��ġ
		setLocationRelativeTo(null); // ȭ�� �߾� ��ġ
		setResizable(false);
		
		JButton btnBack = new JButton("�ڷΰ���");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
		JButton btnUserInfoModify = new JButton("���� ����");
		btnUserInfoModify.setBounds(680, 10, 100, 50);
		btnUserInfoModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserInfoModify(memberDTO);
			}
		});
		
		
		JButton btnTestRecord = new JButton("���� ���");
		btnTestRecord.setBounds(800, 10, 100, 50);
		btnTestRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserTestRecord(memberDTO, memberDTO.getId());
			}
		});
		
		// �����ϴ� ���� �ҷ��� ����
		TestDAO dao = new TestDAO();
		ArrayList<TestDTO> dtos = new ArrayList<TestDTO>();
		
		dtos = dao.selectAllTest();
		
		for(int i = 0; i < dtos.size(); i++) {
			String testId = String.valueOf(dtos.get(i).getId());
			
			JLabel lbl = new JLabel(dtos.get(i).getName());
			lbl.setBounds(posX, posY + (i * 60), 300, 50);
			lbl.setFont(font);
			
			JButton btnTestStart = new JButton("���� ����");
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
		// ���� ����, ���� �����ϱ�
		// ���� �� ��� �����ϱ�
		add(btnUserInfoModify);
		add(btnBack);
		add(btnTestRecord);
		add(scroll);
		setVisible(true);
	}
}