package com.java.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.java.DB.MemberDTO;
import com.java.DB.TestContentDAO;
import com.java.DB.TestContentDTO;
import com.java.DB.TestRecordDAO;
import com.java.DB.TestRecordDTO;
import com.java.DB.TestSubmittedAnswersDAO;
import com.java.DB.TestSubmittedAnswersDTO;

public class Test extends JFrame {
	
	int posX = 70, posY = 20;
	int radioPosX = 0, radioPosY = 170;
	Font font25 = new Font("????", Font.PLAIN, 25);
	Font font20 = new Font("????", Font.PLAIN, 20);
	Font font15 = new Font("????", Font.PLAIN, 15);
	Font font20_bold = new Font("????", Font.BOLD, 20);
	JRadioButton radio[] = new JRadioButton[4];
	int selectedNum = 0;
	int nowPage = 0;
	int test_id;
	ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	
	// ?????? ???ϵ?
	ArrayList<JTextArea> txtAreaList = new ArrayList<JTextArea>();
	ArrayList<JTextField> txtRadioList1 = new ArrayList<JTextField>();
	ArrayList<JTextField> txtRadioList2 = new ArrayList<JTextField>();
	ArrayList<JTextField> txtRadioList3 = new ArrayList<JTextField>();
	ArrayList<JTextField> txtRadioList4 = new ArrayList<JTextField>();
	ArrayList<String> answerList = new ArrayList<String>();
	ArrayList<String> userAnswerList = new ArrayList<String>();
	ArrayList<JTextArea> txtNarrativeAnswerList = new ArrayList<JTextArea>();
	ArrayList<Integer> q_formList = new ArrayList<Integer>();
	
	TestContentDAO dao = new TestContentDAO();
	TestSubmittedAnswersDAO userAnswersDAO = new TestSubmittedAnswersDAO();
	MemberDTO memberDTO;
	ArrayList<TestContentDTO> dtos;
	
	int _timer = 0;
	JLabel lblTimer = new JLabel(); 
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			_timer += 1;
			String second = String.format("%02d", _timer%60);
			String minute = String.format("%02d", _timer/60);
			lblTimer.setText(minute+":"+second);
		}
	};
	Test(MemberDTO memberDTO, String test_id) {
		timer.schedule(task, 1000, 1000);
		this.test_id = Integer.valueOf(test_id);
		this.memberDTO = memberDTO;
		setTitle("???? ???? ??");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null); // ȭ?? ?߾? ??ġ
		setResizable(false);
		
		dtos = dao.selectAllTestContent(test_id);
		
		// ???? ?ҷ??ͼ? ????
		for(int i = 0; i < dtos.size(); i++) { 
			createQ(dtos.get(i));
		}
		lblTimer.setBounds(790,10, 100, 50);
		lblTimer.setFont(font20);
		lblTimer.setText("00:00");
		getContentPane().add(lblTimer);
		getContentPane().add(panelList.get(0));
		
		setVisible(true);
	}
	
	void createQ(TestContentDTO dto) {
		panelList.add(new JPanel());
		int length = panelList.size()-1;
		panelList.get(length).setLayout(null);
		JRadioButton radio[] = new JRadioButton[4];
		ButtonGroup group = new ButtonGroup();
		
		q_formList.add(dto.getQ_form());
		
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
		
		panelList.get(length).add(txtAreaExplanation);
		txtAreaList.add(txtAreaExplanation);
		
        radio[0] = new JRadioButton(dto.getAnswer1());
        radio[0].setBounds(posX+radioPosX, posY+radioPosY, 500, 60);
        radio[0].setFont(font20);
        radio[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userAnswerList.set(length, "1");
			}
		});
        
        panelList.get(length).add(radio[0]);
        
        radio[1] = new JRadioButton(dto.getAnswer2());
        radio[1].setBounds(posX+radioPosX, posY+radioPosY+60, 500, 60);
        radio[1].setFont(font20);
        radio[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userAnswerList.set(length, "2");
			}
		});
        
        panelList.get(length).add(radio[1]);
        
        radio[2] = new JRadioButton(dto.getAnswer3());
        radio[2].setBounds(posX+radioPosX, posY+radioPosY+120, 500, 60);
        radio[2].setFont(font20);
        radio[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userAnswerList.set(length, "3");
			}
		});
        
        panelList.get(length).add(radio[2]);
        
        radio[3] = new JRadioButton(dto.getAnswer4());
        radio[3].setBounds(posX+radioPosX, posY+radioPosY+180, 500, 60);
        radio[3].setFont(font20);
        radio[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userAnswerList.set(length, "4");
			}
		});
        
        panelList.get(length).add(radio[3]);
        
     // ?ְ???
        JTextArea txtNarractiveAnswer = new JTextArea();
        txtNarractiveAnswer.setBounds(posX, posY+170, 430, 220);
        txtNarractiveAnswer.setFont(font20);

        panelList.get(length).add(txtNarractiveAnswer);
        txtNarrativeAnswerList.add(txtNarractiveAnswer);
        
        
        // ??????, ?ְ??? ǥ??
        if(dto.getQ_form() == 0) {
        	radio[0].setVisible(true);
        	radio[1].setVisible(true);
        	radio[2].setVisible(true);
        	radio[3].setVisible(true);
        	txtNarractiveAnswer.setVisible(false);
        }
        else {
        	radio[0].setVisible(false);
        	radio[1].setVisible(false);
        	radio[2].setVisible(false);
        	radio[3].setVisible(false);
        	txtNarractiveAnswer.setVisible(true);
        }
        
        // ???? ????
        answerList.add(dto.getAnswer());
        userAnswerList.add(null);
        if(dto.getQ_form() == 1)
        	userAnswerList.set(length, "");
        
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
				getContentPane().add(lblTimer);
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
				getContentPane().add(lblTimer);
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
				new UserTestList(memberDTO);
			}
		});
        
        panelList.get(length).add(btnBack);
        
        JButton btnSubmit = new JButton("????");
        btnSubmit.setBounds(posX+730, posY+420, 140, 50);
        btnSubmit.setFont(font20);
        btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "?????? ?????Ͻðڽ??ϱ??",
						"?????? ????", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					if(userAnswerList.contains(null))
					{
						JOptionPane.showMessageDialog(null, "Ǯ?? ???? ?????? ?ֽ??ϴ?.", "", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					TestRecordDAO dao = new TestRecordDAO();
					TestRecordDTO dto = new TestRecordDTO();
					
					java.util.Date date = new Date();
					Object datetime = new java.sql.Timestamp(date.getTime());
					System.out.println(datetime);
					
					dto.setTest_id(test_id);
					dto.setM_id(memberDTO.getId());
					dto.setSubmit_datetime(datetime);
					dto.setScore(calcScore());
					
					dao.submitTest(dto);
					
					ArrayList<TestSubmittedAnswersDTO> userAnswerDTOs = new ArrayList<TestSubmittedAnswersDTO>();
					for(int i = 0; i < userAnswerList.size(); i++) {
						if(q_formList.get(i) == 1) userAnswerList.set(i, txtNarrativeAnswerList.get(i).getText());
						TestSubmittedAnswersDTO userAnswerDTO = new TestSubmittedAnswersDTO(0, i+1, userAnswerList.get(i)); 
						userAnswerDTOs.add(userAnswerDTO);
					}
					userAnswersDAO.insertAnswers(userAnswerDTOs);
					setVisible(false);
					new UserTestList(memberDTO);
					JOptionPane.showMessageDialog(null, "?ɸ? ?ð? : " + lblTimer.getText(), "", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
        
        panelList.get(length).add(btnSubmit);
        
        group.add(radio[0]);
        group.add(radio[1]);
        group.add(radio[2]);
        group.add(radio[3]);
	}
	
	int calcScore() {
		int score = 0;
		for(int i = 0; i < answerList.size(); i++) {
			if(q_formList.get(i) == 1) userAnswerList.set(i, txtNarrativeAnswerList.get(i).getText());
			if(answerList.get(i).equals(userAnswerList.get(i))) {
				 score += dtos.get(i).getScore();
			}
		}
		return score;
	}
}
