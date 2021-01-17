import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Demo extends JFrame{
	JRadioButton rbLeft=new JRadioButton("Left",false);
	JRadioButton rbCenter=new JRadioButton("Center",true);
	JRadioButton rbRight=new JRadioButton("Right",false);
	
	JRadioButton rbTop=new JRadioButton("Top",false);
	JRadioButton rbMiddle=new JRadioButton("Middle",true);
	JRadioButton rbBottom=new JRadioButton("Bottom",false);
	ImageIcon icon=new ImageIcon("x.png");
	
	String[] strColors= {"Red", "Blue", "Green", "Cyan", "Magenta", "Yellow","Black"};
	JComboBox comboColor= new JComboBox(strColors);
    
	JCheckBox checkText= new JCheckBox("Text",true);
	JCheckBox checkIcon= new JCheckBox("Icon",true);
	JLabel label=new JLabel("Cross",icon,SwingConstants.CENTER);

	
	public Demo() {
		Container cp= getContentPane();
		
		ButtonGroup topgroup=new ButtonGroup();
		ButtonGroup bottomgroup=new ButtonGroup();
		
	    JPanel topPanel = new JPanel(new GridLayout(1,3));
	    JPanel bottomPanel=new JPanel(new GridLayout(1,3));
	    JPanel leftSide=new JPanel(new GridLayout(2,1));
	    JPanel rightSide=new JPanel();
	    JPanel mainPanel=new JPanel();

	    mainPanel.setLayout(new BorderLayout());
	    topPanel.setBorder(BorderFactory.createTitledBorder("Horizontal Alignment "));
	    topPanel.add(rbLeft);
	    topPanel.add(rbCenter);
	    topPanel.add(rbRight);
	    topgroup.add(rbLeft);
	    topgroup.add(rbCenter);
	    topgroup.add(rbRight);
	    
	    bottomPanel.setBorder(BorderFactory.createTitledBorder("Vertical Alignment "));
	    bottomPanel.add(rbTop);
	    bottomPanel.add(rbMiddle);
	    bottomPanel.add(rbBottom);
	    
	    bottomgroup.add(rbTop);
	    bottomgroup.add(rbMiddle);
	    bottomgroup.add(rbBottom);

	    mainPanel.add(label);

	    
	    //////////////////////Horizontal Alignment 이벤트
	    rbLeft.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		if (e.getSource() == rbLeft) {
	
	    			label.setHorizontalAlignment(JLabel.LEFT);
	    		}
	    		else {
					return;
	    		}
	    	}
	    });
	    
	    rbCenter.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		if (e.getSource() == rbCenter) {
	
	    			label.setHorizontalAlignment(JLabel.CENTER);
	    		}
	    		else {
					return;
	    		}
	    	}
	    });
	    
	    rbRight.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		if (e.getSource() == rbRight) {
	
	    			label.setHorizontalAlignment(JLabel.RIGHT);
	    		}
	    		else {
					return;
	    		}
	    	}
	    });
	

	    
	    //////////////////////Vertical Alignment 이벤트
	    rbTop.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		if (e.getSource() == rbTop) {

	    			label.setVerticalAlignment(JLabel.TOP);
	    		}
	    		else {
					return;
	    		}
	    	}
	    });
	    
	    rbMiddle.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		if (e.getSource() == rbMiddle) {

	    			label.setVerticalAlignment(JLabel.CENTER);
	    		}
	    		else {
					return;
	    		}
	    	}
	    });

	    
	    rbBottom.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		if (e.getSource() == rbBottom) {

	    			label.setVerticalAlignment(JLabel.BOTTOM);
	    		}
	    		else {
					return;
	    		}
	    	}
	    });

	    
	   	leftSide.add(checkText);
	    leftSide.add(checkIcon);

	    ///////////////////checkbox 이벤트
	    checkText.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	        	if(e.getStateChange()==1)
	            label.setText("Cross");
	        	else
	        		label.setText(null);
	        }
	    });

	    checkIcon.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	        	if(e.getStateChange()==1) {
	        		label.setIcon(icon);
	        	}
	        	else
	        		label.setIcon(null);
	        }
	    });

	    leftSide.setPreferredSize(new Dimension(60,90));
	    topPanel.setPreferredSize(new Dimension(100,70));
	    
	    bottomPanel.setPreferredSize(new Dimension(100,70));
	    
	    
	    
	    ///////////////combobox 이벤트
	    comboColor.addActionListener(new ActionListener() {    	
	    	  public void actionPerformed(ActionEvent e) {
	    		  JComboBox cb = (JComboBox)e.getSource();
	    		  int index=cb.getSelectedIndex();
	    		  if(index==0)
					label.setForeground(Color.RED);
	    		  else if(index==1)
	    			  label.setForeground(Color.BLUE);
	    		  else if(index==2)
	    			  label.setForeground(Color.GREEN);
	    		  else if(index==3)
	    			  label.setForeground(Color.CYAN);
	    		  else if(index==4)
	    			  label.setForeground(Color.MAGENTA);
	    		  else if(index==5)
	    			  label.setForeground(Color.YELLOW);
	    		  else if(index==6)
	    			  label.setForeground(Color.BLACK);
			}
		});

	    
	    rightSide.add(comboColor);
	    rightSide.setPreferredSize(new Dimension(90,90));
	    
	    Font font = new Font("arian", Font.BOLD, 30);
	    label.setFont(font);
	    label.setForeground(Color.RED);
	    
	    mainPanel.setPreferredSize(new Dimension(300,300));
	    mainPanel.setBackground(new Color(204, 238, 241)); 
	    
	    cp.add(topPanel,BorderLayout.NORTH,SwingConstants.CENTER);
	    cp.add(bottomPanel,BorderLayout.SOUTH,SwingConstants.CENTER);
	    cp.add(leftSide,BorderLayout.WEST,SwingConstants.CENTER);
	    cp.add(rightSide,BorderLayout.EAST,SwingConstants.CENTER);
	    cp.add(mainPanel,BorderLayout.CENTER);
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Demo");
	    setSize(600, 470);
	    setVisible(true);
	    
	}

	 public static void main(String[] args) {
	    
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new Demo();
	         }
	      });
	   }
}