package com.java.management.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
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
import com.java.management.Management;

public class TestMng extends JFrame {
	
	Font font = new Font("돋움", Font.BOLD, 30);
	int posX = 20, posY = 20;
	int length;
	JPanel pane;
	JScrollPane scroll;
	
	ArrayList<JLabel> lblTestNameList = new ArrayList<JLabel>();
	ArrayList<JButton> btnModifyList = new ArrayList<JButton>();
	ArrayList<JButton> btnDeleteList = new ArrayList<JButton>();
	
	public TestMng(MemberDTO managerDTO) {
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,400);
		
		setTitle("시험 관리");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 절대 위치
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		TestDAO dao = new TestDAO();
		ArrayList<TestDTO> dtos = new ArrayList<TestDTO>();
		
		dtos = dao.selectAllTest();
		length = dtos.size();
		
		for(int i = 0; i < length; i++) {
			String testId = String.valueOf(dtos.get(i).getId());
			
			JLabel lbl = new JLabel(dtos.get(i).getName());
			lbl.setBounds(posX, posY + (i * 60), 300, 50);
			lbl.setFont(font);
			
			JButton btnModify = new JButton("수정");
			btnModify.setBounds(posX + 650, posY + (i * 60), 70, 50);
			btnModify.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new TestEditor(testId, managerDTO);
				}
			});
			
			JButton btnDelete = new JButton("삭제");
			btnDelete.setBounds(posX + 730, posY + (i * 60), 70, 50);
			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?",
							"시험 삭제", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						dao.deleteTest(testId);
						lbl.setVisible(false);
						btnModify.setVisible(false);
						btnDelete.setVisible(false);
						
						// 한 칸씩 당기기
						int index = lblTestNameList.indexOf(lbl);
						
						lblTestNameList.remove(lbl);
						btnModifyList.remove(btnModify);
						btnDeleteList.remove(btnDelete);
						
						for(int i = index; i < lblTestNameList.size(); i++) {
							Point testNamePoint = lblTestNameList.get(i).getLocation();
							testNamePoint.y -= 60;
							Point modifyPoint = btnModifyList.get(i).getLocation();
							modifyPoint.y -= 60;
							Point deletePoint = btnDeleteList.get(i).getLocation();
							deletePoint.y -= 60;
							
							lblTestNameList.get(i).setLocation(testNamePoint);
							btnModifyList.get(i).setLocation(modifyPoint);
							btnDeleteList.get(i).setLocation(deletePoint);
						}
						
						// 스크롤 짧아지게 패널 길이 줄이기
						Dimension di = pane.getPreferredSize();
						di.height -= 60;
						pane.setPreferredSize(di);
					}
				}
			});
			
			lblTestNameList.add(lbl);
			btnModifyList.add(btnModify);
			btnDeleteList.add(btnDelete);
			
			pane.add(lbl);
			pane.add(btnModify);
			pane.add(btnDelete);
			
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(10 , 10 , 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Management(managerDTO);
			}
		});
		
		JButton btnCreateTest = new JButton("시험 생성");
		btnCreateTest.setBounds(800, 10, 120, 50);
		btnCreateTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String resultStr = JOptionPane.showInputDialog("시험의 이름을 입력하세요.");
				if(resultStr == null) return;
				
				TestDTO dto = new TestDTO();
				dto.setMadeby("");
				dto.setName(resultStr);
				dao.createTest(dto);
				
				setVisible(false);
				new TestMng(managerDTO);
			}
		});
		
		add(btnBack);
		add(btnCreateTest);
		add(scroll);
		setVisible(true);
	}
}
