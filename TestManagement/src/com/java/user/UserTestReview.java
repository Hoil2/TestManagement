package com.java.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import com.java.DB.MemberDTO;
import com.java.DB.TestContentDAO;
import com.java.DB.TestContentDTO;
import com.java.DB.TestSubmittedAnswersDAO;

public class UserTestReview extends JFrame {
	int posX = 70, posY = 20;
	int radioPosX = 0, radioPosY = 170;
	int selectedNum = 0;
	int nowPage = 0;
	Font font25 = new Font("????", Font.PLAIN, 25);
	Font font20 = new Font("????", Font.PLAIN, 20);
	Font font15 = new Font("????", Font.PLAIN, 15);
	Font font20_bold = new Font("????", Font.BOLD, 20);
	
	
	ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	
	MemberDTO memberDTO;
	TestSubmittedAnswersDAO userAnswersDAO = new TestSubmittedAnswersDAO();
	TestContentDAO testDAO = new TestContentDAO();
	ArrayList<TestContentDTO> testDTOs;
	ArrayList<String> userAnswers;
	
	String member_id;
	public UserTestReview(MemberDTO memberDTO, String member_id, int test_id, int test_num) {
		this.memberDTO = memberDTO;
		this.member_id = member_id;
		setTitle("???? ????");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // ȭ?? ?߾? ??ġ
		setResizable(false);
		
		testDTOs = testDAO.selectAllTestContent(String.valueOf(test_id));
		userAnswers = userAnswersDAO.selectUserAnswers(test_num);
		for(int i = 0; i < testDTOs.size(); i++) {
			System.out.println(userAnswers.get(i));
			createQ(testDTOs.get(i), userAnswers.get(i));
		}
		
		getContentPane().add(panelList.get(0));
		setVisible(true);
	}
	
	void createQ(TestContentDTO dto, String userAnswer) {
		panelList.add(new JPanel());
		int length = panelList.size()-1;
		panelList.get(length).setLayout(null);
		JRadioButton radio[] = new JRadioButton[4];
		ButtonGroup group = new ButtonGroup();
		
		
		JLabel lblQNum = new JLabel(dto.getQ_num() + ".");
		lblQNum.setBounds(posX-25, posY, 25, 25);
		lblQNum.setFont(font25);
		
		panelList.get(length).add(lblQNum);
		
		JTextArea txtAreaExplanation = new JTextArea(dto.getExplanation());
		txtAreaExplanation.setBounds(posX, posY, 700, 150);
		txtAreaExplanation.setFont(font25);
		txtAreaExplanation.setLineWrap(true); // ?ڵ? ?ٹٲ?
		txtAreaExplanation.setEditable (false);
		txtAreaExplanation.setWrapStyleWord (true); // ?ٹٲ? ???????ְ?
		
		//txtAreaExplanation.setEnabled(false);
		panelList.get(length).add(txtAreaExplanation);
		
		
        radio[0] = new JRadioButton(dto.getAnswer1());
        radio[0].setBounds(posX+radioPosX, posY+radioPosY, 500, 60);
        radio[0].setFont(font20);
        
        panelList.get(length).add(radio[0]);
        
        radio[1] = new JRadioButton(dto.getAnswer2());
        radio[1].setBounds(posX+radioPosX, posY+radioPosY+60, 500, 60);
        radio[1].setFont(font20);
        panelList.get(length).add(radio[1]);
        
        radio[2] = new JRadioButton(dto.getAnswer3());
        radio[2].setBounds(posX+radioPosX, posY+radioPosY+120, 500, 60);
        radio[2].setFont(font20);
        
        panelList.get(length).add(radio[2]);
        
        radio[3] = new JRadioButton(dto.getAnswer4());
        radio[3].setBounds(posX+radioPosX, posY+radioPosY+180, 500, 60);
        radio[3].setFont(font20);
        
        panelList.get(length).add(radio[3]);
        
        JTextArea txtNarractiveAnswer = new JTextArea();
        txtNarractiveAnswer.setBounds(posX, posY+170, 430, 220);
        txtNarractiveAnswer.setFont(font20);
        txtNarractiveAnswer.setEditable(false);
        panelList.get(length).add(txtNarractiveAnswer);
        
        if(dto.getQ_form() == 0) {
        	radio[0].setVisible(true);
        	radio[1].setVisible(true);
        	radio[2].setVisible(true);
        	radio[3].setVisible(true);
        	txtNarractiveAnswer.setVisible(false);
        	if(userAnswer != "")
        		radio[Integer.valueOf(userAnswer)-1].setSelected(true); 
        }
        else {
        	radio[0].setVisible(false);
        	radio[1].setVisible(false);
        	radio[2].setVisible(false);
        	radio[3].setVisible(false);
        	
        	txtNarractiveAnswer.setVisible(true);
        	txtNarractiveAnswer.setText(userAnswer);
        }
        
        //JLabel lblAnswer = new JLabel("???? : " + dto.getAnswer());
        //lblAnswer.setBounds(posX+510, posY+300, 100, 30);
        //lblAnswer.setFont(font20_bold);
        
        //panelList.get(length).add(lblAnswer);
        
        String ox = "";
		if(dto.getAnswer().equals(userAnswer))
			ox = "O";
		else {
			ox = "X";
			if(dto.getQ_form() == 0) {
				radio[Integer.valueOf(userAnswer)-1].setForeground(Color.red);
				radio[Integer.valueOf(dto.getAnswer())-1].setForeground(Color.blue);
				
				JLabel lblAnswerDisplay = new JLabel("->");
				lblAnswerDisplay.setBounds(posX+radioPosX-20, radio[Integer.valueOf(dto.getAnswer())-1].getY(), 30, 63);
				lblAnswerDisplay.setFont(font15);
				panelList.get(length).add(lblAnswerDisplay);
			}
			else {
				JButton btnShowAnswer = new JButton("???? ????");
				btnShowAnswer.setBounds(posX + 450, posY + 190, 140, 50);
				btnShowAnswer.setFont(font20);
				btnShowAnswer.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, dto.getAnswer(), "??", JOptionPane.PLAIN_MESSAGE);
					}
				});
				panelList.get(length).add(btnShowAnswer);
			}
			
		}
		
		JLabel lblOX = new JLabel(ox); 
		lblOX.setBounds(posX-25, posY+35, 25, 25);
		lblOX.setFont(font20_bold);
		
		panelList.get(length).add(lblOX);
        
        JButton btnPrevious = new JButton("???? ????");
        btnPrevious.setBounds(posX, posY+420, 140, 50);
        btnPrevious.setFont(font20);
        btnPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nowPage == 0) {
					return;
				}
				getContentPane().removeAll();					
				getContentPane().add(panelList.get(--nowPage));
				revalidate();
				repaint();
			}
		});
        
        panelList.get(length).add(btnPrevious);
        
        JButton btnNext = new JButton("???? ????");
        btnNext.setBounds(posX+200, posY+420, 140, 50);
        btnNext.setFont(font20);
        btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nowPage == panelList.size()-1) {
					return;
				}
				getContentPane().removeAll();					
				getContentPane().add(panelList.get(++nowPage));
				revalidate();
				repaint();
			}
		});
        
        panelList.get(length).add(btnNext);
        
        JButton btnBack = new JButton("?ڷΰ???");
        btnBack.setBounds(posX+800, posY, 100, 50);
        btnBack.setFont(font15);
        btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserTestRecord(memberDTO, member_id);
			}
		});
        
        panelList.get(length).add(btnBack);
        
        
        group.add(radio[0]);
        group.add(radio[1]);
        group.add(radio[2]);
        group.add(radio[3]);
	}
}
