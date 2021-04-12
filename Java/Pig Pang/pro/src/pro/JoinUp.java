package pro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;    

class JoinUp implements ActionListener{

	public void actionPerformed(ActionEvent e) {

		
			JFrame frame=new JFrame("피그팡");
			frame.setPreferredSize(new Dimension(600,600));
			frame.setLocation(500,400);
			Container contentPane=frame.getContentPane();
			
			JPanel panel1=new JPanel();
			JPanel panel2=new JPanel();
			
			contentPane.add(panel1,BorderLayout.CENTER);
			contentPane.add(panel2,BorderLayout.SOUTH);
			panel1.setBackground(new Color(250,200,205));
			panel2.setBackground(new Color(250,200,205));

			
			panel2.setLayout(new FlowLayout());		
			panel1.setLayout(new FlowLayout());
			
			JTextField Name=new JTextField(5);
			JTextField ID=new JTextField(5);
			JTextField Password=new JTextField(5);
	
			panel1.add(new JLabel("이름: "));
			panel1.add(Name);
			panel1.add(new JLabel("ID: "));
			panel1.add(ID);
			panel1.add(new JLabel("Password: "));
			panel1.add(Password);
			
			JButton btnOK=new JButton("확인");
			panel2.add(btnOK);
			
		
			
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
						(new Startmain(ID.getText())).run();
				}
			});
			
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		}
		

	

}
