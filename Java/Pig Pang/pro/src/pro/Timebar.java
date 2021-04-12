package pro;

	import java.awt.Color;
	import java.awt.Graphics;
	import javax.swing.*;
	
public class Timebar extends JPanel{
	
		
		
		Main myid = new Main();
		
		String showid = myid.user; //Main class의 user 값을 가져옴.
		
		int _count = 100;

		public void paint(Graphics g)  {

			g.clearRect(0, 0, getWidth(), getHeight());

			int width = (int)(getWidth()*_count/100.);				

			int height = getHeight();
			
			//처음엔 연두색으로 표시하다가 제한 시간이 20% 이하이면 빨간색 
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
			/////제한 시간이 끝나면 랭킹 화면을 보여주기 위해 Ranking class의 run메서드를 호출
			if(_count==0) {	
				new Ranking(showid);
				Ranking.run(showid);
			}
		}

	}


