package com.java.ex;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.java.DB.MemberDAO;
import com.java.DB.MemberDTO;
import com.java.management.Management;
import com.java.user.UserTestList;

public class Login extends JFrame {
	
	private JLabel lblID, lblPW;
	private JTextField txtFieldID;
	private JPasswordField txtFieldPW;
	private JButton btnSignIn, btnSignUp;
	
	Point loginPoint;
	
	public Login() {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		System.out.println("utilDate:" + utilDate);
		System.out.println("sqlDate:" + sqlDate);
		
		setTitle("�α���");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // ���� ��ġ
		setLocationRelativeTo(null); // ȭ�� �߾� ��ġ
		setResizable(false);
		
		loginPoint = new Point();
		loginPoint.setLocation(350, 300);
		
		lblID = new JLabel("ID");
		lblID.setBounds(loginPoint.x, loginPoint.y, 100, 30);
		
		lblPW = new JLabel("Password");
		lblPW.setBounds(loginPoint.x, loginPoint.y+50, 100, 30);
		
		txtFieldID = new JTextField(20);
		txtFieldID.setBounds(loginPoint.x+100, loginPoint.y, 200, 30);
		
		txtFieldPW = new JPasswordField(20);
		txtFieldPW.setBounds(loginPoint.x+100, loginPoint.y+50, 200, 30);
		
		btnSignIn = new JButton("�α���");
		btnSignIn.setBounds(loginPoint.x, loginPoint.y+100, 300, 40);
		btnSignIn.addActionListener(new SignInEvent());
		
		btnSignUp = new JButton("ȸ�� ����");
		btnSignUp.setBounds(loginPoint.x, loginPoint.y+170, 300, 30);
		btnSignUp.addActionListener(new SignUpEvent());
		
		add(lblID);
		add(lblPW);
		add(txtFieldID);
		add(txtFieldPW);
		add(btnSignIn);
		add(btnSignUp);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Login login = new Login();
	}
	
	// �α��� �̺�Ʈ
	class SignInEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//setVisible(false);
			//new Management();
			
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = new MemberDTO();
			
			dto = dao.selectMember(txtFieldID.getText());
			
			if(dto.getId().equals("")) {
				JOptionPane.showMessageDialog(null, "�߸��� ID �Ǵ� Password�Դϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(dto.getPw().equals(txtFieldPW.getText())) {
				if(dto.getPosition().equals("user")) {
					// ���� â���� �Ѿ��
					setVisible(false);
					new UserTestList(dto);
					System.out.println("����");
				}
				else if(dto.getPosition().equals("manager")) {
					// ������ â���� �Ѿ��
					setVisible(false);
					new Management(dto);
					System.out.println("������");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "�߸��� ID �Ǵ� Password�Դϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	// ȸ������ �̺�Ʈ
	class SignUpEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new SignUp();
		}
	}
}
