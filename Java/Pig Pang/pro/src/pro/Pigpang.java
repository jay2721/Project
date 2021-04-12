package pro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import javax.swing.*;
import java.util.*;


class Pigpang{
	
	public void gamestart(){

        JFrame frame = new JFrame("게임만들기");
        frame.setLocation(500, 200);
        frame.setPreferredSize(new Dimension(700, 700));
        Container contentPane = frame.getContentPane();
      
        ImgPanel imagePanel = new ImgPanel();
    
        imagePanel.addMouseListener(new MouseHandler(imagePanel));
        JPanel board=new JPanel(); //이미지패널 넣는 패널 
        JPanel sidePanel=new JPanel(); //음악 재생,멈춤 버튼 넣는 패널
        JPanel scorePanel=new JPanel(); //이동 횟수, 점수 표시 넣는 패널
        JPanel timePanel=new JPanel(); //제한시간 표시 패널
        
        sidePanel.setBackground(new Color(255,204,204));
		board.setBackground(new Color(255,204,204));
		scorePanel.setBackground(new Color(255,204,204));
		timePanel.setBackground(new Color(255,204,204));
        
        imagePanel.setPreferredSize(new Dimension(422,422));
        board.add(imagePanel);
        contentPane.add(board,BorderLayout.CENTER,SwingConstants.CENTER);
        
  //////////////////////////////타이머
        
        Timebar timebar =new Timebar();
        Timer m_timer = new Timer();
  
        m_timer.schedule(new TimerTask() {
	        	public void run()
	        	{
	        		timebar.tick();
	        	}
        	}, 0,30); 
        

       ////////////////////////// 음악 재생,멈춤 버튼 생성
       Music music = new Music();
       ImageIcon icon2=new ImageIcon("play.png");
       JButton playButton=new JButton("",icon2); //버튼에 이미지 추가
       //버튼을 누르면 음악이 재생되게 함
       playButton.addActionListener(new ActionListener() {    	
    	   public void actionPerformed(ActionEvent e) {
				music.run("song.mp3");
			}
		});
       
       ImageIcon icon3=new ImageIcon("stop.png");
       JButton stopButton=new JButton("",icon3);//버튼에 이미지 추가
       //버튼을 누르면 음악이 멈추게 함
       stopButton.addActionListener(new ActionListener() {    	
    	   public void actionPerformed(ActionEvent e) {
				music.stop();
			}
		});
      
       ///////////음악 재생,멈춤 버튼 크기 지정
       playButton.setPreferredSize(new Dimension(50,50));
       stopButton.setPreferredSize(new Dimension(50,50));

       sidePanel.add(playButton);
       sidePanel.add(stopButton);
       contentPane.add(sidePanel,BorderLayout.WEST,SwingConstants.CENTER);
       sidePanel.setPreferredSize(new Dimension(70,50));
       

     	/////////////////////////////점수,이동횟수 표시
       
        //그림 추가
       	ImageIcon icon4=new ImageIcon("pinkpig.png");
		JLabel pig=new JLabel(icon4);
		scorePanel.add(pig);
		pig.setBorder(BorderFactory.createEmptyBorder(0,30,0 ,20));
		
		//점수 표시
        JLabel showscore=new JLabel("점수: ");
        scorePanel.add(showscore);
        scorePanel.add(imagePanel.labelscore);
        
        //이동횟수 표시
        JLabel showmove=new JLabel("이동횟수: ");
        scorePanel.add(showmove);
        scorePanel.add(imagePanel.labelmove);
        
        showscore.setFont(showscore.getFont().deriveFont(20.0f));
        showmove.setFont(showscore.getFont().deriveFont(20.0f));
        imagePanel.labelmove.setFont(showscore.getFont().deriveFont(20.0f));
        imagePanel.labelscore.setFont(showscore.getFont().deriveFont(20.0f));
        
        imagePanel.labelscore.setPreferredSize(new Dimension(50,100));
        
		contentPane.add(scorePanel,BorderLayout.NORTH);

		//////////////////////////////////제한 시간 표시
		ImageIcon icon=new ImageIcon("clock.png");
		JLabel labeltime=new JLabel(icon);
		
		labeltime.setBorder(BorderFactory.createEmptyBorder(0,-10,0 ,-10));
		timePanel.add(labeltime);
		timePanel.add(timebar);
		
		labeltime.setPreferredSize(new Dimension(100,100));
		timebar.setPreferredSize(new Dimension(390,45));
		
		contentPane.add(timePanel,BorderLayout.SOUTH);
	

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

