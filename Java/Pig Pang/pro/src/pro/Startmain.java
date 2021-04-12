package pro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;    


class Startmain {

	String userid;
	Startmain(String id){
		userid = id;	
	} // Main class의  ID textfield에 입력한 값을 가져옴.
	
	public void run() {
		
		JFrame frame=new JFrame("피그팡");
		frame.setPreferredSize(new Dimension(600,600));
		frame.setLocation(500,200);
		Container contentPane=frame.getContentPane();
		
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		
		panel1.setLayout(null);
		panel2.setLayout(new FlowLayout());
		
		////////////패널 배경색 설정
		panel1.setBackground(new Color(250,200,205));
		panel2.setBackground(new Color(250,200,205));
		panel3.setBackground(new Color(250,200,205));
		
		contentPane.add(panel1,BorderLayout.CENTER);
		contentPane.add(panel2,BorderLayout.SOUTH);
		contentPane.add(panel3,BorderLayout.NORTH);
		
		//////////////////////돼지 그림 추가
		ImageIcon icon=new ImageIcon("pig.png");
		JLabel pig=new JLabel(icon);
		pig.setBorder(BorderFactory.createEmptyBorder(150, 0 , -10 , 0));
		panel3.add(pig,SwingConstants.CENTER);
	
		///////////////////////게임 하는 사용자의 ID를 보여줌.
		JLabel startlabel=new JLabel(userid+"님으로 게임을 시작합니다.");
		panel1.add(startlabel,SwingConstants.CENTER);
		startlabel.setFont(startlabel.getFont().deriveFont(20.0f));
		startlabel.setBounds(150,0,400,100);
		
		//////////////////////////////게임 시작 버튼 생성
		JButton btnstart=new JButton("start");
		panel2.add(btnstart);
		
		btnstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					(new Pigpang()).gamestart();
			}
		});
		
		///////////////////////////////게임 설명 버튼 생성
		JButton gameinfo=new JButton("게임 설명");
		panel2.add(gameinfo);
		
		gameinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
					(new GameInfo()).run();
			}
		});
				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	

	

}
