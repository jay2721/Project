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
	
     ///////////////////랜덤으로 돌린 숫자에 이미지를 넣어줌.
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
	
	////////////////////////이미지 들어갈 배열에 0~6까지의 숫자를 랜덤으로 돌려줌
	public void rand() {
	     int i,j;
	     for(i=0;i<6;i++) {
	        for(j=0;j<6;j++) {
	           maps[i][j]=(int)(Math.random()*7);
	        }
	     }
	     
	     ////////////////처음 랜덤을 돌릴 때 없어져야할 3개이상 같은 그림이 나오지 않게 하기 위해 다시 돌려줌.
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


   ///////////////////이미지가 보여지는 곳의 크기와 이미지의 크기를 지정해주고 클릭한 이미지에 테두리를 넣어줌. 
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
    
    //////////////클릭을 위한 메서드
    public void clicked(int x, int y)
    {
    	int row = y/70; //////처음으로 클릭한 아이콘의 y좌표(열)
    	int col = x/70; /////처음으로 클릭한 아이콘의 x좌표(행)
    	if ( row > 5 || col > 5 ) return;
    	
    	if ( !_isSelected ) {
    		_selectedRow = row; ///////////////두번째 순서로 클릭한 아이콘의 y좌표(열)
    		_selectedCol = col; //////////////두번째 순서로 클릭한 아이콘의 x좌표(행)
    		_isSelected = true;
    		
    	} else
    	{
    		/////////////////////////아이콘을 상하좌우로만 바꾸게 하는 조건
    		
    		/////////////////양옆으로 바꾸는 조건
    		if(row <= _selectedRow+1 && row >= _selectedRow-1 && col == _selectedCol) {
    			movecount++; 
    		int temp = maps[_selectedRow][_selectedCol];
    		maps[_selectedRow][_selectedCol] = maps[row][col];
    		maps[row][col] = temp;
    		_isSelected = false;

    		}
    		////////////위 아래로 바꾸는 조건
    		else if(col <= _selectedCol+1 && col >= _selectedCol-1 && row == _selectedRow) {
    			movecount++;
        		int temp = maps[_selectedRow][_selectedCol];
        		maps[_selectedRow][_selectedCol] = maps[row][col];
        		maps[row][col] = temp;
        		_isSelected = false;
        		}
    		labelmove.setText(Integer.toString(this.getMove())); //게임화면에 추가한 이동횟수 표시 라벨에 추가하기 위해 Integer를 string으로 바꿈.
    	}
    	repaint();
    	
    	/////////////////////////////처음 클릭한 아이콘과 두번째 순서로 클릭한 아이콘을 바꿨을 때 바꾼 아이콘들과 같은 그림이 있는지 검사하기 위해 row,col로도 검사
    	////////////////////////////row와 col로도 검사하지 않으면 없애고 싶은 아이콘을 무조건 두번째 순서로 클릭해서 바꿔야 없어짐.
    	
    	getRunLengthV(_selectedRow,_selectedCol); //위치를 바꾸고 나서 두번째 클릭한 아이콘과 같은 그림이 있는지 검사하기 위해 _selectedRow,_selectedCol의 값을 넘겨줌
    	getRunLengthH(_selectedRow,_selectedCol );//위치를 바꾸고 나서 두번째 클릭한 아이콘과 같은 그림이 있는지 검사하기 위해 _selectedRow,_selectedCol의 값을 넘겨줌
    	getRunLengthV(row,col); //위치를 바꾸고 나서 처음 클릭한 아이콘과 같은 그림이 있는지 검사 하기 위해 row,col의 값을 넘겨줌.
    	getRunLengthH(row,col); //위치를 바꾸고 나서 처음 클릭한 아이콘과 같은 그림이 있는지 검사 하기 위해 row,col의 값을 넘겨줌.
    	
    	check(); //아이콘들이 내려와 비어있는 자리에 랜덤 이미지로 채우기 위해 호출

    }
    
//////////////////////////////////////////////같은 그림이 있는지 세로(열)로 검사하는 메서드
   void getRunLengthV(int i, int j)
	{
		int topCount=0, bottomCount=0;
		int leftCount=0, rightCount=0;
		int target = maps[i][j];
		int pos;
		
        ///////////////세로부터 검사
		for( pos=i-1 ; pos>=0 && maps[pos][j]==target ; --pos, ++topCount); //pos의 한줄 위 부터 같은 그림이 있나 확인하고 있으면 topCount를 증가
			
		for( pos=i+1 ; pos<6 && maps[pos][j]==target ; ++pos, ++bottomCount); //pos의 한줄 아래 부터 같은 그림이 있나 확인하고 있으면 topCount를 증가
	
		int runlength= (topCount+bottomCount+1); //윗줄의 같은 그림 개수 + 아랫줄의 같은 그림 개수 + 비교 기준이 되는 아이콘

		////////기준 아이콘 포함 같은 그림이 3개 이상이면 3개를 임의의 하얀배경 그림으로 대체
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
			
			
			//////////////////////아이콘 이동 후 ㄱ,ㄴ 모양으로 맞춰질경우 가로도 같이 검사
			for( pos=j-1 ; pos>=0 && maps[i][pos]==target ; --pos, ++leftCount); //pos와 같은 그림이 있나 왼쪽으로 확인하고 있으면 leftCount를 증가
				
			for( pos=j+1 ; pos<6 && maps[i][pos]==target ; ++pos, ++rightCount); //pos와 같은 그림이 있나 오른쪽으로 확인하고 있으면 rightCount를 증가
				
			
			int grunlength=(leftCount+rightCount+1); //왼쪽의 같은 그림 개수 + 오른쪽의 같은 그림 개수 + 비교 기준이 되는 아이콘
			
			////////기준 아이콘 포함 같은 그림이 3개 이상이면 3개를 빈 그림으로 대체
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
	
			scorecount(runlength); //점수 카운트를 위해 runlength를 넘겨 호출
			scorecount(grunlength); //같은 그림이 ㄱ,ㄴ 모양으로 있으면 가로,세로 점수를 같이 카운트 해줘야하기때문에 grunlength를 넘겨 호출
	
		} 

		change(); 

		repaint(); 
		
	
	}
   
   //////////////////////////////////////////////같은 그림이 있는지 가로(행)로 검사하는 메서드
	void getRunLengthH(int i, int j)
	{
		int topCount=0, bottomCount=0;
		int leftCount=0, rightCount=0;
		int target = maps[i][j];
		int pos;

		///////////////가로부터 검사
		for( pos=j-1 ; pos>=0 && maps[i][pos]==target ; --pos, ++leftCount) //pos와 같은 그림이 있나 왼쪽으로 확인하고 있으면 leftCount를 증가
			;
		for( pos=j+1 ; pos<6 && maps[i][pos]==target ; ++pos, ++rightCount) //pos와 같은 그림이 있나 왼쪽으로 확인하고 있으면 rightCount를 증가
			;
		
		int runlength=(leftCount+rightCount+1); //왼쪽의 같은 그림 개수 + 오른쪽의 같은 그림 개수 + 비교 기준이 되는 아이콘

		////////기준 아이콘 포함 같은 그림이 3개 이상이면 3개를 임의의 하얀배경 그림으로 대체
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
			
			//////////////////////아이콘 이동 후 ㄱ,ㄴ 모양으로 맞춰질경우 세로도 같이 검사
			for( pos=i-1 ; pos>=0 && maps[pos][j]==target ; --pos, ++topCount); //pos의 한줄 위 부터 같은 그림이 있나 확인하고 있으면 topCount를 증가
			
			for( pos=i+1 ; pos<6 && maps[pos][j]==target ; ++pos, ++bottomCount); //pos와 같은 그림이 있나 오른쪽으로 확인하고 있으면 rightCount를 증가
				
			int srunlength= (topCount+bottomCount+1); //윗줄의 같은 그림 개수 + 아랫줄의 같은 그림 개수 + 비교 기준이 되는 아이콘
	
			
			////////기준 아이콘 포함 같은 그림이 3개 이상이면 3개를 빈 그림으로 대체
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

			scorecount(runlength); //점수 카운트를 위해 runlength를 넘겨 호출
			scorecount(srunlength); //같은 그림이 ㄱ,ㄴ 모양으로 있으면 가로,세로 점수를 같이 카운트 해줘야하기때문에 srunlength를 넘겨 호출

		}			
 
		change();
		
		repaint();
		
	}

	////////////////////////////////////////////점수 카운트 해주는 메서드
	int scorecount(int runlength) {
		
		if(runlength==3)
			this.setScore(100);
		else if(runlength==4)
			this.setScore(200);
		else if(runlength==5)
			this.setScore(300);
		else if(runlength==6)
			this.setScore(400);
	
		labelscore.setText(Integer.toString(this.getScore())); //게임화면에 추가한 이동횟수 표시 라벨에 추가하기 위해 Integer를 string으로 바꿈.
		return this.getScore();
	}
	  
	////////////////////세개 이상 맞춰 임의의 흰 배경 그림으로 대체된 아이콘들을 그 윗줄에 있는 아이콘로 바꾸게 해 없어지는 것처럼 보이게 해주는 메소드
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
	
	////////////////////////////////////////////아이콘들이 내려와 비어있는 자리에 랜덤 이미지을 돌려주는 메소드
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

