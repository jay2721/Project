package pro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class GameInfo {
	public void run(){
		JFrame frame=new JFrame("피그팡");
		frame.setPreferredSize(new Dimension(600,600));
		frame.setLocation(500,200);
		Container contentPane=frame.getContentPane();
		
		JPanel panel1=new JPanel(); //타이틀(게임 설명)넣는 패널
		JPanel panel2=new JPanel(); //게임 컨셉  및 게임 설명 내용 넣는 패널
		JPanel panel3= new JPanel(); //게임 시작 버튼 넣는 패널
		
		////////여백 설정
		panel1.setBorder(BorderFactory.createEmptyBorder(20 , 0 , 40 , 0));
		
		//////////패널 배경 색 설정
		panel1.setBackground(new Color(250,200,205));
		panel2.setBackground(new Color(250,200,205));
		panel3.setBackground(new Color(250,200,205));
		
		contentPane.add(panel1,BorderLayout.NORTH);
		contentPane.add(panel2,BorderLayout.CENTER);
		contentPane.add(panel3,BorderLayout.SOUTH);
		
		JLabel titlelabel=new JLabel("게임 설명 ",SwingConstants.CENTER);
		titlelabel.setFont(titlelabel.getFont().deriveFont(30.0f));

	
		/////////////////////////게임 컨셉 및 게임 설명 내용
		JTextArea infor=new JTextArea("배고픈 아기 돼지 꿀꿀이를 도와주세요!\r\n" + 
				"보드에서 제거된 디저트들은 꿀꿀이가 먹을 수 있게 됩니다^^\r\n" + 
				"\r\n" + 
				"같은 디저트를 세 개 이상 매치하면  보드에서 제거되며, 점수를 얻을 수 있습니다.\r\n" + 
				"* 세 개의 디저트를 맞추면 100포인트 획득!\r\n" + 
				"* 네 개의 디저트를 맞추면 200포인트 획득!\r\n" + 
				"* 다섯 개의 디저트를 맞추면 300포인트 획득!\r\n" + 
				"* 여섯 개의 디저트를 맞추면 400포인트 획득!\r\n" + 
				"\r\n" + 
				"제한 시간 60초 안에 최대한 많은 포인트를 획득해보세요!");
		
		infor.setFont(titlelabel.getFont().deriveFont(15.0f));
		infor.setBackground(new Color(250,200,205));
		

		panel1.add(titlelabel);
		panel2.add(infor);

		///////////////////////////게임 시작 버튼 생성
		JButton btnstart=new JButton("게임시작");
		panel3.add(btnstart);
		
		btnstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					(new Pigpang()).gamestart();
			}
		});
				
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	
	
	

	

}
