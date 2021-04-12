package pro;

	import java.awt.Color;
	import java.awt.Graphics;
	import javax.swing.*;
	
public class Timebar extends JPanel{
	
		
		
		Main myid = new Main();
		
		String showid = myid.user; //Main class�� user ���� ������.
		
		int _count = 100;

		public void paint(Graphics g)  {

			g.clearRect(0, 0, getWidth(), getHeight());

			int width = (int)(getWidth()*_count/100.);				

			int height = getHeight();
			
			//ó���� ���λ����� ǥ���ϴٰ� ���� �ð��� 20% �����̸� ������ 
			if(_count>=20)
				g.setColor(Color.green);
			else
				g.setColor(Color.red);
			g.fillRect(0, 0, width, height);

		}

		public void tick()
		{
			_count--;
			repaint();
			/////���� �ð��� ������ ��ŷ ȭ���� �����ֱ� ���� Ranking class�� run�޼��带 ȣ��
			if(_count==0) {	
				new Ranking(showid);
				Ranking.run(showid);
			}
		}

	}


