package pro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class GameInfo {
	public void run(){
		JFrame frame=new JFrame("�Ǳ���");
		frame.setPreferredSize(new Dimension(600,600));
		frame.setLocation(500,200);
		Container contentPane=frame.getContentPane();
		
		JPanel panel1=new JPanel(); //Ÿ��Ʋ(���� ����)�ִ� �г�
		JPanel panel2=new JPanel(); //���� ����  �� ���� ���� ���� �ִ� �г�
		JPanel panel3= new JPanel(); //���� ���� ��ư �ִ� �г�
		
		////////���� ����
		panel1.setBorder(BorderFactory.createEmptyBorder(20 , 0 , 40 , 0));
		
		//////////�г� ��� �� ����
		panel1.setBackground(new Color(250,200,205));
		panel2.setBackground(new Color(250,200,205));
		panel3.setBackground(new Color(250,200,205));
		
		contentPane.add(panel1,BorderLayout.NORTH);
		contentPane.add(panel2,BorderLayout.CENTER);
		contentPane.add(panel3,BorderLayout.SOUTH);
		
		JLabel titlelabel=new JLabel("���� ���� ",SwingConstants.CENTER);
		titlelabel.setFont(titlelabel.getFont().deriveFont(30.0f));

	
		/////////////////////////���� ���� �� ���� ���� ����
		JTextArea infor=new JTextArea("����� �Ʊ� ���� �ܲ��̸� �����ּ���!\r\n" + 
				"���忡�� ���ŵ� ����Ʈ���� �ܲ��̰� ���� �� �ְ� �˴ϴ�^^\r\n" + 
				"\r\n" + 
				"���� ����Ʈ�� �� �� �̻� ��ġ�ϸ�  ���忡�� ���ŵǸ�, ������ ���� �� �ֽ��ϴ�.\r\n" + 
				"* �� ���� ����Ʈ�� ���߸� 100����Ʈ ȹ��!\r\n" + 
				"* �� ���� ����Ʈ�� ���߸� 200����Ʈ ȹ��!\r\n" + 
				"* �ټ� ���� ����Ʈ�� ���߸� 300����Ʈ ȹ��!\r\n" + 
				"* ���� ���� ����Ʈ�� ���߸� 400����Ʈ ȹ��!\r\n" + 
				"\r\n" + 
				"���� �ð� 60�� �ȿ� �ִ��� ���� ����Ʈ�� ȹ���غ�����!");
		
		infor.setFont(titlelabel.getFont().deriveFont(15.0f));
		infor.setBackground(new Color(250,200,205));
		

		panel1.add(titlelabel);
		panel2.add(infor);

		///////////////////////////���� ���� ��ư ����
		JButton btnstart=new JButton("���ӽ���");
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
