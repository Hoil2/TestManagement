package com.java.management.user;

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
import javax.swing.JScrollPane;

import com.java.DB.MemberDAO;
import com.java.DB.MemberDTO;
import com.java.management.Management;
import com.java.management.test.TestEditor;
import com.java.user.UserTestRecord;

public class UserMng extends JFrame{
	
	Font font = new Font("돋움", Font.BOLD, 20);
	int posX = 20, posY = 0;
	
	JPanel pane;
	JScrollPane scroll;
	
	public UserMng(MemberDTO managerDTO) {
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 10));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,400);
		
		setTitle("회원 관리");
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
				new Management(managerDTO);
			}
		});
		
		JLabel _lblID = new JLabel("ID");
		_lblID.setBounds(posX+120, posY+20, 150, 50);
		_lblID.setFont(font);
		
		JLabel _lblName = new JLabel("이름");
		_lblName.setBounds(posX + 320, posY+20, 150, 50);
		_lblName.setFont(font);
		
		JLabel _lblEmail = new JLabel("Email");
		_lblEmail.setBounds(posX + 520, posY+20, 300, 50);
		_lblEmail.setFont(font);
		add(_lblID);
		add(_lblName);
		add(_lblEmail);
		
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		dtos = dao.selectAllMember();
		
		int cnt = 0;
		for(int i = 0; i < dtos.size(); i++) {
			if(dtos.get(i).getPosition().equals("user")) {
				String m_id = dtos.get(i).getId();
				
				JLabel lblID = new JLabel(dtos.get(i).getId());
				lblID.setBounds(posX + 40, posY + 10 + (cnt * 60), 150, 50);
				lblID.setFont(font);
				
				JLabel lblName = new JLabel(dtos.get(i).getName());
				lblName.setBounds(posX + 240, posY + 10 + (cnt * 60), 150, 50);
				lblName.setFont(font);
				
				JLabel lblEmail = new JLabel(dtos.get(i).getEmail());
				lblEmail.setBounds(posX + 440, posY + 10 + (cnt * 60), 300, 50);
				lblEmail.setFont(font);
				
				JButton btnModify = new JButton("세부 정보 확인");
				btnModify.setBounds(posX + 640, posY + 10 + (cnt * 60), 130, 50);
				btnModify.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						new UserTestRecord(managerDTO, m_id);
					}
				});
				
				pane.add(lblID);
				pane.add(lblName);
				pane.add(lblEmail);
				pane.add(btnModify);
				cnt++;
				
				Dimension di = pane.getPreferredSize();
				di.height += 60;
				pane.setPreferredSize(di);
			}
		}
		
		add(scroll);
		add(btnBack);
		setVisible(true);
	}
}
