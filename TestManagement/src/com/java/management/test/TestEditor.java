package com.java.management.test;

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
import javax.swing.JTextField;

import com.java.DB.MemberDTO;
import com.java.DB.TestContentDAO;
import com.java.DB.TestContentDTO;

public class TestEditor extends JFrame {
	
	int posX = 70, posY = 20;
	int radioPosX = 0, radioPosY = 170; 
	Font font25 = new Font("돋움", Font.PLAIN, 25);
	Font font20 = new Font("돋움", Font.PLAIN, 20);
	Font font20_bold = new Font("돋움", Font.BOLD, 20);
	int selectedNum = 0;
	int nowPage = 0;
	int testid;
	ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	
	// 저장할 목록들
	ArrayList<JTextArea> txtAreaList = new ArrayList<JTextArea>();
	ArrayList<JTextField> txtRadioList1 = new ArrayList<JTextField>();
	ArrayList<JTextField> txtRadioList2 = new ArrayList<JTextField>();
	ArrayList<JTextField> txtRadioList3 = new ArrayList<JTextField>();
	ArrayList<JTextField> txtRadioList4 = new ArrayList<JTextField>();
	ArrayList<String> answerList = new ArrayList<String>();
	ArrayList<JTextField> txtScoreList = new ArrayList<JTextField>();
	ArrayList<Integer> q_formList = new ArrayList<Integer>();
	ArrayList<JTextArea> txtNarrativeAnswerList = new ArrayList<JTextArea>();
	TestContentDAO dao = new TestContentDAO();
	MemberDTO managerDTO;
	TestEditor() {
		
	}
	
	TestEditor(String test_id, MemberDTO managerDTO) {
		this.managerDTO = managerDTO;
		testid = Integer.valueOf(test_id);
		setTitle("시험 편집");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(null); // JPanel 사용시 프레임을 절대위치로 하면 안 됨
		setLocationRelativeTo(null); // 화면 중앙 배치
		setResizable(false);
		
		
		ArrayList<TestContentDTO> dtos = new ArrayList<TestContentDTO>();
		
		dtos = dao.selectAllTestContent(test_id);
		
		// 문제가 없을 때
		if(dtos.size() == 0) {
			TestContentDTO t_dto = new TestContentDTO();
			t_dto.setTest_id(testid);
			t_dto.setQ_num(answerList.size()+1);
			t_dto.setQ_form(0);
			t_dto.setExplanation("");
			t_dto.setAnswer1("");
			t_dto.setAnswer2("");
			t_dto.setAnswer3("");
			t_dto.setAnswer4("");
			t_dto.setAnswer("");
			t_dto.setScore(0);
			t_dto.setImage(null);
			createQ(t_dto);
			dao.addTestContent(t_dto);
		}
		else {
			// 문제 불러와서 생성
			for(int i = 0; i < dtos.size(); i++) { // 테스트용 1
				createQ(dtos.get(i));
			}
		}
		getContentPane().add(panelList.get(0)); // 기본 페이지
		setVisible(true);
	}
	
	// ---------------------------
	// 문제 생성 함수
	// ---------------------------
	void createQ(TestContentDTO dto) {
		panelList.add(new JPanel());
		int length = panelList.size()-1;
		panelList.get(length).setLayout(null);
		JRadioButton answerRadios[] = new JRadioButton[4];
		ButtonGroup answerGroup = new ButtonGroup();
		JRadioButton q_formRadios[] = new JRadioButton[2];
		ButtonGroup q_formGroup = new ButtonGroup();
		
		// 기본 문제 유형
        q_formList.add(dto.getQ_form());
		
		JLabel lblQNum = new JLabel(dto.getQ_num() + ".");
		lblQNum.setBounds(posX-25, posY, 25, 25);
		lblQNum.setFont(font25);
		
		panelList.get(length).add(lblQNum);
		
		JTextArea txtAreaExplanation = new JTextArea(dto.getExplanation());
		txtAreaExplanation.setBounds(posX, posY, 700, 150);
		txtAreaExplanation.setFont(font25);
		txtAreaExplanation.setLineWrap(true); // 자동 줄바꿈
		
		panelList.get(length).add(txtAreaExplanation);
		txtAreaList.add(txtAreaExplanation);
		
		// 객관식
        answerRadios[0] = new JRadioButton();
        answerRadios[0].setBounds(posX+radioPosX, posY+radioPosY, 30, 30);
        answerRadios[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				answerList.set(length, "1");
				System.out.println("설정");
			}
		});
        
        panelList.get(length).add(answerRadios[0]);
        
        JTextField txtRadio1 = new JTextField(dto.getAnswer1());
        txtRadio1.setBounds(posX+radioPosX+30, posY+radioPosY, 400, 40);
        txtRadio1.setFont(font20);
        
        panelList.get(length).add(txtRadio1);
        txtRadioList1.add(txtRadio1);
        
        answerRadios[1] = new JRadioButton();
        answerRadios[1].setBounds(posX+radioPosX, posY+radioPosY+60, 30, 30);
        answerRadios[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				answerList.set(length, "2");
				System.out.println("설정");
			}
		});
        
        panelList.get(length).add(answerRadios[1]);
        
        
        
        JTextField txtRadio2 = new JTextField(dto.getAnswer2());
        txtRadio2.setBounds(posX+radioPosX+30, posY+radioPosY+60, 400, 40);
        txtRadio2.setFont(font20);
        
        panelList.get(length).add(txtRadio2);
        txtRadioList2.add(txtRadio2);
        
        answerRadios[2] = new JRadioButton();
        answerRadios[2].setBounds(posX+radioPosX, posY+radioPosY+120, 30, 30);
        answerRadios[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				answerList.set(length, "3");
				System.out.println("설정");
			}
		});
        
        panelList.get(length).add(answerRadios[2]);
        
        JTextField txtRadio3 = new JTextField(dto.getAnswer3());
        txtRadio3.setBounds(posX+radioPosX+30, posY+radioPosY+120, 400, 40);
        txtRadio3.setFont(font20);
        
        panelList.get(length).add(txtRadio3);
        txtRadioList3.add(txtRadio3);
        
        answerRadios[3] = new JRadioButton();
        answerRadios[3].setBounds(posX+radioPosX, posY+radioPosY+180, 30, 30);
        answerRadios[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				answerList.set(length, "4");
				System.out.println("설정");
			}
		});
        
        panelList.get(length).add(answerRadios[3]);
        
        JTextField txtRadio4 = new JTextField(dto.getAnswer4());
        txtRadio4.setBounds(posX+radioPosX+30, posY+radioPosY+180, 400, 40);
        txtRadio4.setFont(font20);
        
        panelList.get(length).add(txtRadio4);
        txtRadioList4.add(txtRadio4);
        
        
        // 주관식
        JTextArea txtNarractiveAnswer = new JTextArea();
        txtNarractiveAnswer.setBounds(posX, posY+170, 430, 220);
        txtNarractiveAnswer.setFont(font20);

        panelList.get(length).add(txtNarractiveAnswer);
        txtNarrativeAnswerList.add(txtNarractiveAnswer);
        
        
        // 객관식, 주관식 표시
        if(dto.getQ_form() == 0) {
        	answerRadios[0].setVisible(true);
        	answerRadios[1].setVisible(true);
        	answerRadios[2].setVisible(true);
        	answerRadios[3].setVisible(true);
        	txtRadio1.setVisible(true);
        	txtRadio2.setVisible(true);
        	txtRadio3.setVisible(true);
        	txtRadio4.setVisible(true);
        	txtNarractiveAnswer.setVisible(false);
        	if(!dto.getAnswer().equals("")) {
        		answerRadios[Integer.valueOf(dto.getAnswer())-1].setSelected(true);
        	}
        }
        else {
        	answerRadios[0].setVisible(false);
        	answerRadios[1].setVisible(false);
        	answerRadios[2].setVisible(false);
        	answerRadios[3].setVisible(false);
        	txtRadio1.setVisible(false);
        	txtRadio2.setVisible(false);
        	txtRadio3.setVisible(false);
        	txtRadio4.setVisible(false);
        	
        	txtNarractiveAnswer.setVisible(true);
        	txtNarractiveAnswer.setText(dto.getAnswer());
        }
        answerList.add(dto.getAnswer());
        
        JButton btnPrevious = new JButton("이전 문제");
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
        
        JButton btnNext = new JButton("다음 문제");
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
        
        // 객관식 or 서술형 선택
        q_formRadios[0] = new JRadioButton("객관식");
        q_formRadios[0].setBounds(posX+450, posY+180, 90, 40);
        q_formRadios[0].setFont(font20);
        q_formRadios[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				q_formList.set(length, 0);
				answerRadios[0].setVisible(true);
	        	answerRadios[1].setVisible(true);
	        	answerRadios[2].setVisible(true);
	        	answerRadios[3].setVisible(true);
	        	txtRadio1.setVisible(true);
	        	txtRadio2.setVisible(true);
	        	txtRadio3.setVisible(true);
	        	txtRadio4.setVisible(true);
	        	
	        	txtNarractiveAnswer.setVisible(false);
			}
		});
        
        panelList.get(length).add(q_formRadios[0]);
        
        q_formRadios[1] = new JRadioButton("서술형");
        q_formRadios[1].setBounds(posX+540, posY+180, 90, 40);
        q_formRadios[1].setFont(font20);
        q_formRadios[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				q_formList.set(length, 1);
				
				answerRadios[0].setVisible(false);
	        	answerRadios[1].setVisible(false);
	        	answerRadios[2].setVisible(false);
	        	answerRadios[3].setVisible(false);
	        	txtRadio1.setVisible(false);
	        	txtRadio2.setVisible(false);
	        	txtRadio3.setVisible(false);
	        	txtRadio4.setVisible(false);
	        	
	        	txtNarractiveAnswer.setVisible(true);
			}
		});
        
        panelList.get(length).add(q_formRadios[1]);
        
        q_formRadios[dto.getQ_form()].setSelected(true);
        
        // 문제 점수        
        JLabel lblScore = new JLabel("점수");
        lblScore.setBounds(posX+450, posY+225, 60, 50);
        lblScore.setFont(font20);
        
        panelList.get(length).add(lblScore);
        
        JTextField txtScore = new JTextField(String.valueOf(dto.getScore()));
        txtScore.setBounds(posX+500, posY+225, 60, 50);
        txtScore.setFont(font20);
        
        panelList.get(length).add(txtScore);
        txtScoreList.add(txtScore);
        
        
        // 문제 추가
        JButton btnAddQ = new JButton("문제 추가");
        btnAddQ.setBounds(posX+450, posY+280, 140, 50);
        btnAddQ.setFont(font20);
        btnAddQ.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestContentDTO t_dto = new TestContentDTO();
				t_dto.setTest_id(dto.getTest_id());
				t_dto.setQ_num(answerList.size()+1);
				t_dto.setExplanation("");
				t_dto.setAnswer1("");
				t_dto.setAnswer2("");
				t_dto.setAnswer3("");
				t_dto.setAnswer4("");
				t_dto.setAnswer("");
				t_dto.setImage(null);
				createQ(t_dto);
				
				dao.addTestContent(t_dto);
				
				// 패널 이동
				getContentPane().removeAll();
				nowPage = answerList.size()-1;
				getContentPane().add(panelList.get(nowPage));
				revalidate();
				repaint();
			}
		});
        
        panelList.get(length).add(btnAddQ);
        
        // 문제 삭제
        JButton btnDelete = new JButton("문제 삭제");
        btnDelete.setBounds(posX+450, posY+340, 140, 50);
        btnDelete.setFont(font20);
        btnDelete.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 데이터 저장
				updateDatabase();
				
				// 선택된 문제 삭제
				int q_num = dto.getQ_num();
				dao.deleteTestContent(testid, q_num);
				
				// 폼 재시작
				setVisible(false);
				new TestEditor(String.valueOf(testid), managerDTO);
				
			}
		});
        
        panelList.get(length).add(btnDelete);
        
        JButton btnBack = new JButton("뒤로가기");
        btnBack.setBounds(posX+750, posY+420, 130, 50);
        btnBack.setFont(font20);
        btnBack.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < answerList.size(); i++) {
					if(answerList.get(i).equals("")) {
						System.out.println("정답 x : " + answerList.get(i));
						JOptionPane.showMessageDialog(null, "정답을 입력하지 않았습니다.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				// 저장하기
				updateDatabase();
				
				setVisible(false);
				new TestMng(managerDTO);
			}
		});
        
        panelList.get(length).add(btnBack);
        
        answerGroup.add(answerRadios[0]);
        answerGroup.add(answerRadios[1]);
        answerGroup.add(answerRadios[2]);
        answerGroup.add(answerRadios[3]);
        q_formGroup.add(q_formRadios[0]);
        q_formGroup.add(q_formRadios[1]);
	}
	
	void updateDatabase() {
		ArrayList<TestContentDTO> t_dtos = new ArrayList<TestContentDTO>();
		
		for(int i = 0; i < answerList.size(); i++) {
			TestContentDTO t_dto = new TestContentDTO();
			t_dto.setTest_id(testid);
			t_dto.setQ_num(i+1);
			t_dto.setExplanation(txtAreaList.get(i).getText());
			t_dto.setAnswer1(txtRadioList1.get(i).getText());
			t_dto.setAnswer2(txtRadioList2.get(i).getText());
			t_dto.setAnswer3(txtRadioList3.get(i).getText());
			t_dto.setAnswer4(txtRadioList4.get(i).getText());
			if(q_formList.get(i) == 0) // 객관식일 때
				t_dto.setAnswer(answerList.get(i));
			else // 주관식일 때
				t_dto.setAnswer(txtNarrativeAnswerList.get(i).getText());
			t_dto.setQ_form(q_formList.get(i)); 
			t_dto.setScore(Integer.valueOf(txtScoreList.get(i).getText()));
			t_dtos.add(t_dto);
		}
		dao.updateTestContent(t_dtos);
	}
}
