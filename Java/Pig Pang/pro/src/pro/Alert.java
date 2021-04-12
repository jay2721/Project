package pro;

import java.awt.*;
import javax.swing.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class Alert {
	 
	 public void run(){
		JFrame frame = new JFrame("Warning");
		frame.setLocation(500,200);
		frame.setPreferredSize(new Dimension(600,600));
		Container contentPane = frame.getContentPane();

		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		contentPane.add(panel1,BorderLayout.NORTH,SwingConstants.CENTER);
		contentPane.add(panel2,BorderLayout.WEST,SwingConstants.CENTER);
		contentPane.add(panel3,BorderLayout.SOUTH);
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 150 , 0 , 0));
		panel1.setBorder(BorderFactory.createEmptyBorder(45, 0 , 30 , 0));
		panel3.setBorder(BorderFactory.createEmptyBorder(0, 0 , 30 , 0));
		
		contentPane.setBackground(new Color(250,200,205));
		panel1.setBackground(new Color(250,200,205));
		panel2.setBackground(new Color(250,200,205));
		panel3.setBackground(new Color(250,200,205));
		
		panel1.add(new JLabel("로그인을 하세요",SwingConstants.CENTER));
         JButton b_check = new JButton("로그인");
        contentPane.add(b_check);
        panel3.add(b_check);

        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new FlowLayout());

		
		JTextField ID=new JTextField(7);
		JTextField Password=new JTextField(7);

	
		panel2.add(new JLabel("ID: "));
		panel2.add(ID);
		panel2.add(new JLabel("      "));
		panel2.add(new JLabel("Password: "));
		panel2.add(Password);
		
		JButton b_joinUp=new JButton("회원가입");

		panel3.add(b_joinUp);
	

		ActionListener listener1=new JoinUp();
		b_joinUp.addActionListener(listener1);

		b_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ID.getText().equals("") || Password.getText().equals(""))     	
		        	(new Alert()).run();     
				else
					(new Startmain(ID.getText())).run();
			}
		});

//        ActionListener listener1=new Main();
//        b_check.addActionListener(listener1);       
        
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}


}