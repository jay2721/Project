package pro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Ranking {
	Main idlabel=new Main();

	static String userid;
	Ranking(String id){
		userid = id;
	}
	public static String getID() {
		return userid;
	}
	
	
	public void setID(String id) {
		this.userid =id;
	}

	public static void run(String id) {
		 Main showid=new Main();
		ImgPanel rankscore=new ImgPanel();
		JFrame frame=new JFrame("피그팡");
		frame.setPreferredSize(new Dimension(600,600));
		frame.setLocation(500,400);
		Container contentPane=frame.getContentPane();

		JPanel panel=new JPanel();
		JPanel listPanel=new JPanel();
		
		listPanel.setBorder(BorderFactory.createEmptyBorder(0, 120 , 0 , 0));
		listPanel.setBackground(new Color(255,204,204));
		
		JLabel title=new JLabel("랭킹",SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(100,50));
		title.setFont(title.getFont().deriveFont(30.0f));
		
		panel.setBackground(new Color(255,204,204));
		panel.add(title);
		panel.setSize(100,100);

	////////////////////게임을 다시 실행하기 위한 게임 시작 버튼 생성
		JButton b_start=new JButton("게임 시작");
		b_start.setFont(b_start.getFont().deriveFont(30.0f));
		b_start.setPreferredSize(new Dimension(100,50));
		b_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					(new Pigpang()).gamestart();
			}
		});
		
	       
			ImgPanel imagePanel = new ImgPanel();
	    
	        int scoremessage =imagePanel.score; //ImgPanel의 score값을 가져옴.
	        String idmessage=getID(); //Main class의 user값을 가져옴.
	        
	        File file = new File("score.txt"); //생성할 파일
	        FileWriter writer = null;
	        
	        ///////////////파일 입력
	        try {
	            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
	            writer = new FileWriter(file, true);
	            writer.write(idmessage+": "+scoremessage+"\r\n"); //파일에 아이디와 점수 저장
	            writer.flush(); 
	        
	        } 
	        catch(IOException e) {
	            e.printStackTrace();
	        } 
	        finally {
	            try {
	                if(writer != null) writer.close();
	            } catch(IOException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        BufferedReader bReader = null;
		
	        ///////////////////////////파일 내용 출력
	        try {
            
            String s;
            
            bReader = new BufferedReader(new FileReader(file));
            
            // 더이상 읽어들일게 없을 때까지 읽어들이게 합니다.
            while((s = bReader.readLine()) != null) {
            	
            	////////////TextArea를 생성하고 파일 내용 추가
                JTextArea rank=new JTextArea(s);
                rank.setFont(rank.getFont().deriveFont(15.0f));
                
                rank.setPreferredSize(new Dimension(200,200));
                rank.setBounds(100, 100, 300, 300); 
                rank.setBackground(new Color(255,204,204));
                listPanel.add(rank,SwingConstants.CENTER);
                contentPane.add(listPanel,BorderLayout.CENTER,SwingConstants.CENTER);
            }
        } 
	        catch(IOException e) {
            e.printStackTrace();
        } 
	        finally {
	        	try {
                if(bReader != null) bReader.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

		contentPane.add(panel,BorderLayout.NORTH);
		contentPane.add(b_start,BorderLayout.SOUTH);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}



}
