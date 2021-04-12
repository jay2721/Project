package pro;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.color.*;

 class Main {
	
	public static String user;
	static ImageIcon icon;	
	
	public String getID() {
		return user;
	}
	
	
	public void setID(String id) {
		this.user =id;
	}

	public static void main(String[] args) {

		    JFrame frame=new JFrame("피그팡");
		
			frame.setPreferredSize(new Dimension(600,600));
			frame.setLocation(500,300);
			Container contentPane=frame.getContentPane();
	       
			////////////////////////배경 이미지 넣기
			icon = new ImageIcon("pigpig2.jpg");
	        JPanel panel = new JPanel() {        
		    public void paintComponent(Graphics g) {
		       g.drawImage(icon.getImage(), 0, 0, null);
		       setOpaque(false);
		       super.paintComponent(g);
		      }
		    };
	      
			JPanel panel1=new JPanel(); //아이디 패스워드 입력 칸 넣는 패널
			JPanel panel2=new JPanel(); //회원가입 버튼 넣는 패널
			JPanel panel3=new JPanel(); //로그인 버튼 넣는 패널
			
			panel1.setLayout(new FlowLayout());
			panel2.setLayout(new FlowLayout());
			
			/////////////////여백 설정
			panel1.setBorder(BorderFactory.createEmptyBorder(20, 0 , 0 , 0));
			panel2.setBorder(BorderFactory.createEmptyBorder(0, 0 , 20 , 0));
			panel3.setBorder(BorderFactory.createEmptyBorder(20, 0 , 0 , 0));
			

			panel.add(panel1);
			panel.add(panel2);
			panel.add(panel3);
	
			////////////////패널 배경색 설정
			panel1.setBackground(new Color(255,204,204));
			panel2.setBackground(new Color(255,204,204));
			panel3.setBackground(new Color(255,204,204));
			
			contentPane.add(panel,BorderLayout.CENTER);
			contentPane.add(panel2,BorderLayout.SOUTH);

			contentPane.add(panel);
			//////////////////////////////아이디 패스워드 입력 칸 생성
			JTextField ID=new JTextField(7);
			JTextField Password=new JTextField(7);
	
			panel1.add(new JLabel("ID: "));
			panel1.add(ID);
			panel1.add(new JLabel("Password: "));
			panel1.add(Password);
			
			///////////////////////////////회원 가입 버튼 생성
			JButton b_joinUp=new JButton("회원가입");
			panel2.add(b_joinUp);
			
			ActionListener listener1=new JoinUp();
			b_joinUp.addActionListener(listener1);
			
			/////////////////////////////로그인 버튼 생성
			JButton b_login=new JButton("로그인");
			panel3.add(b_login);
		
			////////////ID또는 PW textfield 칸이 비었을 때 로그인 버튼을 누르면 경고창으로 넘어가고, 아니라면 게임 준비 화면으로 넘어감.
			b_login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(ID.getText().equals("") || Password.getText().equals(""))     	
			        	(new Alert()).run();     
					else {
						user = ID.getText();
						(new Startmain(user)).run();
					}
				}
			});
			
			Ranking rank=new Ranking(user);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		}



 }
 
 





