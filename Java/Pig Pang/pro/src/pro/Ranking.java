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
		JFrame frame=new JFrame("�Ǳ���");
		frame.setPreferredSize(new Dimension(600,600));
		frame.setLocation(500,400);
		Container contentPane=frame.getContentPane();

		JPanel panel=new JPanel();
		JPanel listPanel=new JPanel();
		
		listPanel.setBorder(BorderFactory.createEmptyBorder(0, 120 , 0 , 0));
		listPanel.setBackground(new Color(255,204,204));
		
		JLabel title=new JLabel("��ŷ",SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(100,50));
		title.setFont(title.getFont().deriveFont(30.0f));
		
		panel.setBackground(new Color(255,204,204));
		panel.add(title);
		panel.setSize(100,100);

	////////////////////������ �ٽ� �����ϱ� ���� ���� ���� ��ư ����
		JButton b_start=new JButton("���� ����");
		b_start.setFont(b_start.getFont().deriveFont(30.0f));
		b_start.setPreferredSize(new Dimension(100,50));
		b_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					(new Pigpang()).gamestart();
			}
		});
		
	       
			ImgPanel imagePanel = new ImgPanel();
	    
	        int scoremessage =imagePanel.score; //ImgPanel�� score���� ������.
	        String idmessage=getID(); //Main class�� user���� ������.
	        
	        File file = new File("score.txt"); //������ ����
	        FileWriter writer = null;
	        
	        ///////////////���� �Է�
	        try {
	            // ���� ������ ���뿡 �̾ ������ true��, ���� ������ ���ְ� ���� ������ false�� �����Ѵ�.
	            writer = new FileWriter(file, true);
	            writer.write(idmessage+": "+scoremessage+"\r\n"); //���Ͽ� ���̵�� ���� ����
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
		
	        ///////////////////////////���� ���� ���
	        try {
            
            String s;
            
            bReader = new BufferedReader(new FileReader(file));
            
            // ���̻� �о���ϰ� ���� ������ �о���̰� �մϴ�.
            while((s = bReader.readLine()) != null) {
            	
            	////////////TextArea�� �����ϰ� ���� ���� �߰�
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
