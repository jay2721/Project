import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.*;


public class MemoCalendar extends JFrame{
	//////////////���//
	  Connection con = null;
      Statement stmt = null;
      
      //DB ������
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://localhost/ayeong?useSSL=false";
      String user = "root";
      String pwd = "2721";
      ResultSet rs = null;       
	int memocount;
	
	///////////////////////////////
	Container cp= getContentPane();
	
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day=cal.get(Calendar.DATE);
	final static int CAL_HEIGHT = 6;
	final static int CAL_WIDTH = 7;
	int listcount=0;
	String selecting;
	 int selectmonth;
	 int selectyear;
	 String selectdate;
	 
	JPanel calPanel = new JPanel();
	JPanel selectPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	String[] stryear= {"2019","2020", "2021", "2022"};
	JComboBox yearbox = new JComboBox(stryear);
	String[] strmonth= {"1", "2", "3", "4", "5", "6","7","8","9","10","11","12"};
	JComboBox monthbox = new JComboBox(strmonth);
	JLabel yearLabel = new JLabel("��");
	JLabel monthLabel = new JLabel("��");
	String[] strday = {"��", "��", "ȭ", "��", "��", "��", "��"};
	JButton dayname[] = new JButton[7];
	JButton dateBtn[][] = new JButton[CAL_HEIGHT][CAL_WIDTH];
	int date[][]=new int [CAL_HEIGHT][CAL_WIDTH];
	

	JPanel memoPanel = new JPanel();
	JPanel todoPanel = new JPanel();
	JPanel bPanel = new JPanel();
	JLabel mLabel = new JLabel("TODO LIST");
	JTextField addmemo = new JTextField(23);
	JButton addbutton = new JButton("�߰�");
	 JCheckBox list= new JCheckBox(addmemo.getText());
	Font font = new Font("arian", Font.BOLD, 15);
	Font font2 = new Font("arian", Font.BOLD, 20);
	
	
	public MemoCalendar() {
		dbConnect();
		cp.setLayout(new BorderLayout());
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		///////////////////////cal//////////////////////////////////
		
		calPanel.setLayout(new BorderLayout());
		
		yearbox.setPreferredSize(new Dimension(70,30));
		monthbox.setPreferredSize(new Dimension(70,30));
		yearbox.setFont(font); 
		monthbox.setFont(font);
		
		yearLabel.setBorder(BorderFactory.createEmptyBorder(0, 0 , 0 , 30));
		yearLabel.setFont(font2); 
		monthLabel.setFont(font2); 
		
		selectPanel.add(yearbox);
		selectPanel.add(yearLabel);
		selectPanel.add(monthbox);
		selectPanel.add(monthLabel);
		selectPanel.setBorder(BorderFactory.createEmptyBorder(15 , 10 , 10 , 0));
		selectPanel.setPreferredSize(new Dimension(450,70));
		
		yearbox.setSelectedIndex(0);
		monthbox.setSelectedIndex(11);
		
	    yearbox.addActionListener(new ActionListener() {    	
	    	  public void actionPerformed(ActionEvent e) {
	    		 year = yearbox.getSelectedIndex()+2019;
	    		
	    		 setdate(year,month);
	    		
			}
		});
	    
	    monthbox.addActionListener(new ActionListener() {    	
	    	  public void actionPerformed(ActionEvent e) {
	    		 month = monthbox.getSelectedIndex();
	    	
	    		 setdate(year,month);
	    		
			}
		});
		

		for(int i=0 ; i<dayname.length; i++){
			dayname[i]=new JButton(strday[i]);
			dayname[i].setPreferredSize(new Dimension(55, 35));
			dayname[i].setBorderPainted(false);
			dayname[i].setContentAreaFilled(false); //Ŭ�������� ��ư ���� ������ �ʰ��ϴ°�
			dayname[i].setBackground(new Color(211, 211, 211));

			if(i == 0) 
				dayname[i].setForeground(Color.RED);
			else if (i == 6) 
				dayname[i].setForeground(Color.BLUE);
			else 
				dayname[i].setForeground(Color.BLACK);
			
			dayname[i].setOpaque(true); //��ư �������ϰ� �ϴ°�
			dayname[i].setFocusPainted(false); //Ŭ�������� �۾� �׸��׵θ� ���ִ°�
			mainPanel.add(dayname[i]);
		}
		
		for(int i=0 ; i<CAL_HEIGHT ; i++){
			for(int j=0 ; j<CAL_WIDTH ; j++){
				dateBtn[i][j]=new JButton();
				dateBtn[i][j].setPreferredSize(new Dimension(55, 35));
				dateBtn[i][j].setBorderPainted(false);
				dateBtn[i][j].setContentAreaFilled(false);
				dateBtn[i][j].setBackground(Color.WHITE);
				dateBtn[i][j].setOpaque(true);
				mainPanel.add(dateBtn[i][j]);
				
				dateBtn[i][j].addActionListener(new ActionListener() { 
					
			    	  public void actionPerformed(ActionEvent e) {
			    		  
			    		  selectdate=(e.getActionCommand()+"");
			    		  selectyear=year;
			    		  selectmonth=month+1;
			    		  selecting=selectyear+"-"+selectmonth+"-"+selectdate;
			    		  readmemo();
			    		  
			    		System.out.println(memocount);
			    		todoPanel.repaint();	
					}
			    	  
				});
			
			}
		}
		
		addbutton.addActionListener(new ActionListener() {    	
			   public void actionPerformed(ActionEvent e) {
			    	adddata(memocount,selecting,addmemo.getText(),0);
			    		    	 readmemo();
			    		    	
			    			}
			    });
		
		mainPanel.setLayout(new GridLayout(0,7,3,3));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(-5 , 0, 0 , 0));
		
		setdate(year,month);

		calPanel.add(mainPanel);
		calPanel.add(selectPanel,BorderLayout.NORTH);
		calPanel.setPreferredSize(new Dimension(550,400));
		calPanel.setBorder(BorderFactory.createEmptyBorder(10 , 20, 0 , 0));
		////////////////////////////////////////////////////////////
		
		
		/////////////////////memo///////////////////////////////////
		
		memoPanel.setLayout(new BorderLayout(10,10));
		
		mLabel.setBorder(BorderFactory.createEmptyBorder(10 , 0, 10 , 0));
		mLabel.setHorizontalAlignment(JLabel.CENTER);
		mLabel.setFont(font2); 
		
		todoPanel.setSize(new Dimension(300,300));
		todoPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		addbutton.setPreferredSize(new Dimension(100, 40));
		addmemo.setPreferredSize(new Dimension(20,40));
		
		calPanel.setBorder(BorderFactory.createEmptyBorder(0 , 30 , 20 , 0));
		
		bPanel.setLayout(new BorderLayout());
		bPanel.setBorder(BorderFactory.createEmptyBorder(10 , 0 , 10 , 0));
		bPanel.add(addmemo,BorderLayout.WEST);
		bPanel.add(addbutton,BorderLayout.EAST);		
		bPanel.setPreferredSize(new Dimension(400,70));

	    memoPanel.setBorder(BorderFactory.createEmptyBorder(10 , 0, 10 , 30));
		memoPanel.add(mLabel,BorderLayout.NORTH,SwingConstants.CENTER);
		memoPanel.add(bPanel,BorderLayout.SOUTH);
		memoPanel.setPreferredSize(new Dimension(400,400));
		memoPanel.add(todoPanel,SwingConstants.CENTER);
		
		////////////////////////////////////////////////////////////////

		cp.add(calPanel,BorderLayout.WEST);
		cp.add(memoPanel,BorderLayout.EAST);
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("MemoCalendar");
	    setSize(1000,500);
	    setVisible(true);
	}
	
	//////////////��¥ �� ���
	public void setdate(int year, int month) {
		
		cal.set(year, month,1);
		//Calendar.DAY_OF_MONTH: ���� ���� ��¥ (DATE�� ����) 
		// Calendar.DAY_OF_WEEK: calendar�� ����Ű��(�ǹ��ϴ�) Ư�� ��¥�� ���� �������� �˱� ���� ����. ��1 ��2 ȭ3~
		///firstday�� �ϸ� ��¥ �Ͽ����� 1�Ϻ��� �����Ҷ� ���� �����.
//		int firstday = cal.get(Calendar.DAY_OF_WEEK) ���� ��Ÿ���⶧���� ��1��2ȭ3 �̷��� �Ǿ �Ϸ羿 �и�;
		
		int firstdaystart = (cal.get(Calendar.DAY_OF_WEEK)+7-(cal.get(Calendar.DAY_OF_MONTH))%7)%7;
		int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


		//�޷� �迭 �ʱ�ȭ
		for(int i = 0 ; i<CAL_HEIGHT ; i++){
			for(int j = 0 ; j<CAL_WIDTH; j++){
				date[i][j] = 0;
			}
		}
		
		for(int i = 0, num = 1, k = 0 ; i<CAL_HEIGHT ; i++){
			if(i == 0) 
				k = firstdaystart;
			else 
				k = 0;
			for(int j = k ; j<CAL_WIDTH; j++){
				if(num <= lastday) 
					date[i][j]=num++;
			}
		}
		
		showCal();

	}
	
	///////////�޷� ��ư�� ��¥ ǥ��
	private void showCal(){
		
		for(int i=0 ; i<CAL_HEIGHT ; i++){
			for(int j=0 ; j<CAL_WIDTH ; j++){
			
				if(j == 0) 
					dateBtn[i][j].setForeground(Color.RED);

				else if (j == 6) 
					dateBtn[i][j].setForeground(Color.BLUE);
				else 
					dateBtn[i][j].setForeground(Color.BLACK);
				
				dateBtn[i][j].setText(""+date[i][j]);
				
				dateBtn[i][j].removeAll();

				if(date[i][j] == 0) {
					dateBtn[i][j].setVisible(false);
				}
				else 
					dateBtn[i][j].setVisible(true);
				
			}
		}
		
	}

	///////�޸� �Է��� ���� db�� �߰��ϴ� �Լ�
	public void adddata(int memocount,String selecting,String memo,int checking) { 
      
//		PreparedStatement �� Statement�� ���� ū �������� ĳ��(cache) ��뿩���̴�.
		
		PreparedStatement pstmt = null;

		try{
		
		// 2. �����ϱ�
		con = DriverManager.getConnection(url, "root", "2721");
		
		
		// 3. SQL ���� �غ�

		String sql = "INSERT INTO todo VALUES (?,?,?,?)";
	
		pstmt = con.prepareStatement(sql);
		

		// 4. ������ binding

		pstmt.setInt(1,memocount+1);
		pstmt.setString(2, selecting);
		pstmt.setString(3, memo);
		pstmt.setInt(4, checking);
		
		
		
		// 5. ���� ���� �� ��� ó��
		// ��ȯ�Ǵ� �����Ͱ� ��� ResultSet ��ü�� �ʿ� ����, �ٷ� pstmt.executeUpdate()�޼��带 ȣ���ϸ� �˴ϴ�.
		int count = pstmt.executeUpdate();
		
		if( count == 0 ){
		   System.out.println("������ �Է� ����");
		}
		else{
		   System.out.println("������ �Է� ����");
		}
		}
		
		catch( SQLException e){
		System.out.println("���� " + e);
		}
		finally{
		try{
		   if( con != null && !con.isClosed()){
		       con.close();
		   }
		   if( pstmt != null && !pstmt.isClosed()){
		       pstmt.close();
		   }
		}
		catch( SQLException e){
		   e.printStackTrace();
			}
		}
		
		
	}

	///////////db���� �о����
	public void readmemo() {
		todoPanel.removeAll();
		listcount=0;
	
	        Statement stmt = null;
	        Statement stmt2 = null;
	        ResultSet rs = null;
	        ResultSet rs2=null;
		 try{
	            // 1. ����̹� �ε�
//	            Class.forName(driver);

	            // 2. �����ϱ�
	            con = DriverManager.getConnection(url, "root", "2721");


	            // 3. ���� ������ ���� Statement ��ü ����
	            stmt = con.createStatement();
	            stmt2=con.createStatement();
	            // 4. SQL ���� �ۼ�
	            String sql = "SELECT * FROM todo WHERE Date='"+selecting+"'";
	            String sql2="SELECT * FROM todo";


	            // 5. ���� ����
	            // ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
	            rs = stmt.executeQuery(sql);
	            rs2 = stmt2.executeQuery(sql2);
	          
	            
	            while(rs2.next()) {
	            	memocount=rs2.getRow();
	            }
	            
	            // 6. ������ ����ϱ�
	            
	            while(rs.next()){
	    
	                String date = rs.getString(2);
	                String memo = rs.getString(3);
	                int check = rs.getInt(4);
	                int number=rs.getInt(1);
	            
	                System.out.println(number+" "+date + " " + memo + " " + check);
	                
		    		listcount++;

		    		todoPanel.setLayout(new GridLayout(listcount,1));
	                JCheckBox list =new JCheckBox(memo);
	                todoPanel.add(list);
	                list.setFont(font);
	                if(check==1) {
	                	list.setForeground(Color.GRAY);
	                	list.setSelected(true);
	                }
	                else if(check==0)
	                	list.setForeground(Color.BLACK);
	                
	        		list.addItemListener(new ItemListener() {    	
	      			  public void itemStateChanged(ItemEvent e) {
	      		        	if(e.getStateChange()==1) {
	      		        		list.setForeground(Color.GRAY);
	      		        		Update(1,number);
	      		        		
	      		        	}
	      		        	else {
	      		        		list.setForeground(Color.BLACK);
	      		        		Update(0,number);
	      		        	}
	      		        }
	      		});
	
	            }
	            
	    
		 }
		   catch( SQLException e){
	            System.out.println("���� " + e);
	        }
	        finally{
	            try{
	                if( con != null && !con.isClosed()){
	                    con.close();
	                }
	                if( stmt != null && !stmt.isClosed()){
	                    stmt.close();
	                }
	                if( rs != null && !rs.isClosed()){
	                    rs.close();
	                }
	            }  catch( SQLException e){
	                e.printStackTrace();
	            }
	        }
		 
	}
	
	///////üũ�ڽ� üũ ���� db�� ����
	public void Update(int checking,int number )  {
		
		PreparedStatement pstmt = null;    
		
	    String sql = "UPDATE `ayeong`.`todo` SET `Check` = ? WHERE (`number` = ?)";

        try {
    		con = DriverManager.getConnection(url, "root", "2721");
    		
    		pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, checking);
            pstmt.setInt(2, number);
     
           pstmt.executeUpdate();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

}

	//////////db����
	  public void dbConnect(){
          try{
                 Class.forName(driver);
                 con = DriverManager.getConnection(url,user,pwd);
                 stmt = con.createStatement();  
                 System.out.println("���� ����");
                 
          }catch(Exception ex){
                 ex.printStackTrace();
          }
          
    }

	 public static void main(String[] args) {
		    
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new MemoCalendar();
	         }
	      });
	   }
}
