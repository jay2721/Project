package pro;

import java.util.*;
import java.awt.*;        
import javax.swing.*;  

public class ImgPanel extends JPanel {


    Image image[] = new Image[9];
    Toolkit toolkit = getToolkit();
    
	public static int maps[][]=new int[6][6];
	public static int score;
	public static int movecount;
	
    boolean _isSelected = false;
    int _selectedRow, _selectedCol;
    
	JLabel labelscore=new JLabel("0");
	JLabel labelmove=new JLabel("0");
	
     ///////////////////�������� ���� ���ڿ� �̹����� �־���.
	public ImgPanel() 
	{ 
		rand();
		for(int i=0; i < image.length; ++i)
			image[i] = toolkit.getImage("img0"+i+".png");
	}
	
	
	public int getScore() {
		return score;
	}
	
	
	public void setScore(int score) {
		this.score += score;
	}
	
	public int getMove() {
		return movecount;
	}
	
	
	public void setMove(int move) {
		this. movecount +=  movecount;
	}
	
	////////////////////////�̹��� �� �迭�� 0~6������ ���ڸ� �������� ������
	public void rand() {
	     int i,j;
	     for(i=0;i<6;i++) {
	        for(j=0;j<6;j++) {
	           maps[i][j]=(int)(Math.random()*7);
	        }
	     }
	     
	     ////////////////ó�� ������ ���� �� ���������� 3���̻� ���� �׸��� ������ �ʰ� �ϱ� ���� �ٽ� ������.
	     for(i=4;i>0;i--) {
	        for(j=1;j<=4;j++){
	           maps[i][j]=(int)(Math.random()*7);
	           
	           if(i>=0 && (maps[i][j] == maps[i+1][j])&&(maps[i][j]==maps[i-1][j]))
	              maps[i][j] = (int)(Math.random()*7);
	           else if(j>=0 && (maps[i][j] == maps[i][j+1])&&(maps[i][j]==maps[i][j-1]))
	              maps[i][j] = (int)(Math.random()*7);
	        }

	     }
	
	  }


   ///////////////////�̹����� �������� ���� ũ��� �̹����� ũ�⸦ �������ְ� Ŭ���� �̹����� �׵θ��� �־���. 
    public void paint(Graphics g)  {
        g.clearRect(0, 0, getWidth(), getHeight());
       
        for(int row=0; row < 6; ++row)
        	for(int col=0; col < 6; ++col)
        		g.drawImage(image[maps[row][col]], col*70, row*70, 70, 70, this);
        
         if (_isSelected) {
        	int x = _selectedCol*70;
        	int y = _selectedRow*70;
        	Graphics2D g2=(Graphics2D)g;
        	g2.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,0));
        	g2.drawRect(x, y, 70, 70);
        }
  
    }
    
    //////////////Ŭ���� ���� �޼���
    public void clicked(int x, int y)
    {
    	int row = y/70; //////ó������ Ŭ���� �������� y��ǥ(��)
    	int col = x/70; /////ó������ Ŭ���� �������� x��ǥ(��)
    	if ( row > 5 || col > 5 ) return;
    	
    	if ( !_isSelected ) {
    		_selectedRow = row; ///////////////�ι�° ������ Ŭ���� �������� y��ǥ(��)
    		_selectedCol = col; //////////////�ι�° ������ Ŭ���� �������� x��ǥ(��)
    		_isSelected = true;
    		
    	} else
    	{
    		/////////////////////////�������� �����¿�θ� �ٲٰ� �ϴ� ����
    		
    		/////////////////�翷���� �ٲٴ� ����
    		if(row <= _selectedRow+1 && row >= _selectedRow-1 && col == _selectedCol) {
    			movecount++; 
    		int temp = maps[_selectedRow][_selectedCol];
    		maps[_selectedRow][_selectedCol] = maps[row][col];
    		maps[row][col] = temp;
    		_isSelected = false;

    		}
    		////////////�� �Ʒ��� �ٲٴ� ����
    		else if(col <= _selectedCol+1 && col >= _selectedCol-1 && row == _selectedRow) {
    			movecount++;
        		int temp = maps[_selectedRow][_selectedCol];
        		maps[_selectedRow][_selectedCol] = maps[row][col];
        		maps[row][col] = temp;
        		_isSelected = false;
        		}
    		labelmove.setText(Integer.toString(this.getMove())); //����ȭ�鿡 �߰��� �̵�Ƚ�� ǥ�� �󺧿� �߰��ϱ� ���� Integer�� string���� �ٲ�.
    	}
    	repaint();
    	
    	/////////////////////////////ó�� Ŭ���� �����ܰ� �ι�° ������ Ŭ���� �������� �ٲ��� �� �ٲ� �����ܵ�� ���� �׸��� �ִ��� �˻��ϱ� ���� row,col�ε� �˻�
    	////////////////////////////row�� col�ε� �˻����� ������ ���ְ� ���� �������� ������ �ι�° ������ Ŭ���ؼ� �ٲ�� ������.
    	
    	getRunLengthV(_selectedRow,_selectedCol); //��ġ�� �ٲٰ� ���� �ι�° Ŭ���� �����ܰ� ���� �׸��� �ִ��� �˻��ϱ� ���� _selectedRow,_selectedCol�� ���� �Ѱ���
    	getRunLengthH(_selectedRow,_selectedCol );//��ġ�� �ٲٰ� ���� �ι�° Ŭ���� �����ܰ� ���� �׸��� �ִ��� �˻��ϱ� ���� _selectedRow,_selectedCol�� ���� �Ѱ���
    	getRunLengthV(row,col); //��ġ�� �ٲٰ� ���� ó�� Ŭ���� �����ܰ� ���� �׸��� �ִ��� �˻� �ϱ� ���� row,col�� ���� �Ѱ���.
    	getRunLengthH(row,col); //��ġ�� �ٲٰ� ���� ó�� Ŭ���� �����ܰ� ���� �׸��� �ִ��� �˻� �ϱ� ���� row,col�� ���� �Ѱ���.
    	
    	check(); //�����ܵ��� ������ ����ִ� �ڸ��� ���� �̹����� ä��� ���� ȣ��

    }
    
//////////////////////////////////////////////���� �׸��� �ִ��� ����(��)�� �˻��ϴ� �޼���
   void getRunLengthV(int i, int j)
	{
		int topCount=0, bottomCount=0;
		int leftCount=0, rightCount=0;
		int target = maps[i][j];
		int pos;
		
        ///////////////���κ��� �˻�
		for( pos=i-1 ; pos>=0 && maps[pos][j]==target ; --pos, ++topCount); //pos�� ���� �� ���� ���� �׸��� �ֳ� Ȯ���ϰ� ������ topCount�� ����
			
		for( pos=i+1 ; pos<6 && maps[pos][j]==target ; ++pos, ++bottomCount); //pos�� ���� �Ʒ� ���� ���� �׸��� �ֳ� Ȯ���ϰ� ������ topCount�� ����
	
		int runlength= (topCount+bottomCount+1); //������ ���� �׸� ���� + �Ʒ����� ���� �׸� ���� + �� ������ �Ǵ� ������

		////////���� ������ ���� ���� �׸��� 3�� �̻��̸� 3���� ������ �Ͼ��� �׸����� ��ü
		if(runlength>=3) {
			for( pos=i-1 ; pos>=0 && maps[pos][j]==target ; --pos)
			{
				maps[i][j]=7;
				maps[pos][j]=7;
			}
			for( pos=i+1 ; pos<6 && maps[pos][j]==target ; ++pos) {
				if(maps[i][j] != 7)
					maps[i][j]=7;	
				maps[pos][j]=7;
			}
			
			
			//////////////////////������ �̵� �� ��,�� ������� ��������� ���ε� ���� �˻�
			for( pos=j-1 ; pos>=0 && maps[i][pos]==target ; --pos, ++leftCount); //pos�� ���� �׸��� �ֳ� �������� Ȯ���ϰ� ������ leftCount�� ����
				
			for( pos=j+1 ; pos<6 && maps[i][pos]==target ; ++pos, ++rightCount); //pos�� ���� �׸��� �ֳ� ���������� Ȯ���ϰ� ������ rightCount�� ����
				
			
			int grunlength=(leftCount+rightCount+1); //������ ���� �׸� ���� + �������� ���� �׸� ���� + �� ������ �Ǵ� ������
			
			////////���� ������ ���� ���� �׸��� 3�� �̻��̸� 3���� �� �׸����� ��ü
			if(grunlength>=3) {
				for( pos=j-1 ; pos>=0 && maps[i][pos]==target ; --pos) {
				maps[i][pos]=7; 
				maps[i][j]=7;
				}
				for( pos=j+1 ; pos<6 && maps[i][pos]==target ; ++pos) {
					if(maps[i][j] != 7)
						maps[i][j] =7;
					maps[i][pos]=7;
				}
			}
	
			scorecount(runlength); //���� ī��Ʈ�� ���� runlength�� �Ѱ� ȣ��
			scorecount(grunlength); //���� �׸��� ��,�� ������� ������ ����,���� ������ ���� ī��Ʈ ������ϱ⶧���� grunlength�� �Ѱ� ȣ��
	
		} 

		change(); 

		repaint(); 
		
	
	}
   
   //////////////////////////////////////////////���� �׸��� �ִ��� ����(��)�� �˻��ϴ� �޼���
	void getRunLengthH(int i, int j)
	{
		int topCount=0, bottomCount=0;
		int leftCount=0, rightCount=0;
		int target = maps[i][j];
		int pos;

		///////////////���κ��� �˻�
		for( pos=j-1 ; pos>=0 && maps[i][pos]==target ; --pos, ++leftCount) //pos�� ���� �׸��� �ֳ� �������� Ȯ���ϰ� ������ leftCount�� ����
			;
		for( pos=j+1 ; pos<6 && maps[i][pos]==target ; ++pos, ++rightCount) //pos�� ���� �׸��� �ֳ� �������� Ȯ���ϰ� ������ rightCount�� ����
			;
		
		int runlength=(leftCount+rightCount+1); //������ ���� �׸� ���� + �������� ���� �׸� ���� + �� ������ �Ǵ� ������

		////////���� ������ ���� ���� �׸��� 3�� �̻��̸� 3���� ������ �Ͼ��� �׸����� ��ü
		if(runlength>=3) {
			for( pos=j-1 ; pos>=0 && maps[i][pos]==target ; --pos) {
			maps[i][pos]=7;
			maps[i][j]=7;

			}
			for( pos=j+1 ; pos<6 && maps[i][pos]==target ; ++pos) {
				if(maps[i][j] !=7)
					maps[i][j] = 7;
				maps[i][pos]=7;
			}
			
			//////////////////////������ �̵� �� ��,�� ������� ��������� ���ε� ���� �˻�
			for( pos=i-1 ; pos>=0 && maps[pos][j]==target ; --pos, ++topCount); //pos�� ���� �� ���� ���� �׸��� �ֳ� Ȯ���ϰ� ������ topCount�� ����
			
			for( pos=i+1 ; pos<6 && maps[pos][j]==target ; ++pos, ++bottomCount); //pos�� ���� �׸��� �ֳ� ���������� Ȯ���ϰ� ������ rightCount�� ����
				
			int srunlength= (topCount+bottomCount+1); //������ ���� �׸� ���� + �Ʒ����� ���� �׸� ���� + �� ������ �Ǵ� ������
	
			
			////////���� ������ ���� ���� �׸��� 3�� �̻��̸� 3���� �� �׸����� ��ü
			if(srunlength>=3) {
				for( pos=i-1 ; pos>=0 && maps[pos][j]==target ; --pos)
				{
					maps[i][j]=7;
					maps[pos][j]=7;
				}
				for( pos=i+1 ; pos<6 && maps[pos][j]==target ; ++pos) {
					if(maps[i][j] != 7)
						maps[i][j]=7;	
					maps[pos][j]=7;
				}
			}

			scorecount(runlength); //���� ī��Ʈ�� ���� runlength�� �Ѱ� ȣ��
			scorecount(srunlength); //���� �׸��� ��,�� ������� ������ ����,���� ������ ���� ī��Ʈ ������ϱ⶧���� srunlength�� �Ѱ� ȣ��

		}			
 
		change();
		
		repaint();
		
	}

	////////////////////////////////////////////���� ī��Ʈ ���ִ� �޼���
	int scorecount(int runlength) {
		
		if(runlength==3)
			this.setScore(100);
		else if(runlength==4)
			this.setScore(200);
		else if(runlength==5)
			this.setScore(300);
		else if(runlength==6)
			this.setScore(400);
	
		labelscore.setText(Integer.toString(this.getScore())); //����ȭ�鿡 �߰��� �̵�Ƚ�� ǥ�� �󺧿� �߰��ϱ� ���� Integer�� string���� �ٲ�.
		return this.getScore();
	}
	  
	////////////////////���� �̻� ���� ������ �� ��� �׸����� ��ü�� �����ܵ��� �� ���ٿ� �ִ� �����ܷ� �ٲٰ� �� �������� ��ó�� ���̰� ���ִ� �޼ҵ�
	void change() {
		int i=0;
		int j=0;
		int temp;
		
		
		for(i=5;i>=1;i--) {
			for(j=0;j<6;j++) {
				if(maps[i][j]==7) {	
					temp = maps[i][j];
					maps[i][j] = maps[i-1][j];
					maps[i-1][j] = temp;
				}
			}
		}

		check();

	}
	
	////////////////////////////////////////////�����ܵ��� ������ ����ִ� �ڸ��� ���� �̹����� �����ִ� �޼ҵ�
	void check() {
		int i=0;
		int j=0;
		
		
		for(i=0;i<6;i++) {
			for(j=0;j<6;j++) {
				if(maps[i][j]==7) 	
					maps[i][j]=(int)(Math.random()*7);
			}
		}
		
		repaint();
	}

	

}

