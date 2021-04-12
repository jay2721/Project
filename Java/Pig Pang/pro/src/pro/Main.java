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

		    JFrame frame=new JFrame("�Ǳ���");
		
			frame.setPreferredSize(new Dimension(600,600));
			frame.setLocation(500,300);
			Container contentPane=frame.getContentPane();
	       
			////////////////////////��� �̹��� �ֱ�
			icon = new ImageIcon("pigpig2.jpg");
	        JPanel panel = new JPanel() {        
		    public void paintComponent(Graphics g) {
		       g.drawImage(icon.getImage(), 0, 0, null);
		       setOpaque(false);
		       super.paintComponent(g);
		      }
		    };
	      
			JPanel panel1=new JPanel(); //���̵� �н����� �Է� ĭ �ִ� �г�
			JPanel panel2=new JPanel(); //ȸ������ ��ư �ִ� �г�
			JPanel panel3=new JPanel(); //�α��� ��ư �ִ� �г�
			
			panel1.setLayout(new FlowLayout());
			panel2.setLayout(new FlowLayout());
			
			/////////////////���� ����
			panel1.setBorder(BorderFactory.createEmptyBorder(20, 0 , 0 , 0));
			panel2.setBorder(BorderFactory.createEmptyBorder(0, 0 , 20 , 0));
			panel3.setBorder(BorderFactory.createEmptyBorder(20, 0 , 0 , 0));
			

			panel.add(panel1);
			panel.add(panel2);
			panel.add(panel3);
	
			////////////////�г� ���� ����
			panel1.setBackground(new Color(255,204,204));
			panel2.setBackground(new Color(255,204,204));
			panel3.setBackground(new Color(255,204,204));
			
			contentPane.add(panel,BorderLayout.CENTER);
			contentPane.add(panel2,BorderLayout.SOUTH);

			contentPane.add(panel);
			//////////////////////////////���̵� �н����� �Է� ĭ ����
			JTextField ID=new JTextField(7);
			JTextField Password=new JTextField(7);
	
			panel1.add(new JLabel("ID: "));
			panel1.add(ID);
			panel1.add(new JLabel("Password: "));
			panel1.add(Password);
			
			///////////////////////////////ȸ�� ���� ��ư ����
			JButton b_joinUp=new JButton("ȸ������");
			panel2.add(b_joinUp);
			
			ActionListener listener1=new JoinUp();
			b_joinUp.addActionListener(listener1);
			
			/////////////////////////////�α��� ��ư ����
			JButton b_login=new JButton("�α���");
			panel3.add(b_login);
		
			////////////ID�Ǵ� PW textfield ĭ�� ����� �� �α��� ��ư�� ������ ���â���� �Ѿ��, �ƴ϶�� ���� �غ� ȭ������ �Ѿ.
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
 
 





