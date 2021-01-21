import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyButton extends JButton
{
	static int count=0;
	int index;
	public MyButton(String s)
	{
		super(s);
		index = count++;
	}
	
}

public class ImgPuzzle extends JFrame implements ActionListener {
	MyButton[] buttons;
	MyButton divide;
	MyButton reset;
	private int pieces = 4;
	private int totalPieces = pieces * pieces;
	private int[] pieceNumber;
	private BufferedImage img;
	
	private JButton piece[] = new JButton[totalPieces];
	private ImageIcon[] icon = new ImageIcon[totalPieces];
	
	public ImgPuzzle() {
		
		super("ImgPuzzle");
		try {
			img = ImageIO.read(new File("hubble.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
  
		}pieceNumber = new int[totalPieces];
		
		for (int i = 0; i < totalPieces-1; i++) {
			pieceNumber[i] = i;
		}
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 4, 2, 2));
		buttons= new MyButton[16];
		
		for(int i=0; i<15; i++)
			buttons[i] =  new MyButton(""+(i+1));
		buttons[15] = new MyButton(" ");
		for(int i=0; i<16; i++)
			panel.add(buttons[i]);
		
		for(int i=0; i<16; i++)
			buttons[i].addActionListener(this);
		add(panel, BorderLayout.CENTER);
		int pieceWidth = img.getWidth(null) / pieces;
		int pieceHeight = img.getHeight(null) / pieces;
		int i=0;
	
		    for (int y = 0; y < pieces; y++) {
		    	   int sy = y * pieceWidth;	
			for (int x = 0; x < pieces; x++) {
		       int sx = x * pieceHeight;
			int number = pieceNumber[y* pieces + x];
			int dx = (number / pieces) * pieceHeight;
			int dy = (number % pieces) * pieceWidth;


		if(i!=15) {
			icon[pieceNumber[i]] = new ImageIcon(img.getSubimage(sx, sy, pieceWidth, pieceHeight)); 
			buttons[i].setIcon(icon[i]);
			i++;
			
		}

		reset=new MyButton("reset");
		reset.setBackground(Color.yellow);  
		reset.setForeground(Color.red);
		divide = new MyButton("Divide");
		divide.setBackground(Color.red);  
		divide.setForeground(Color.yellow);  
		
		add(divide, BorderLayout.SOUTH);
		add(reset,BorderLayout.NORTH);
		
		 divide.addActionListener(new ActionListener() {    	
	    	  public void actionPerformed(ActionEvent e) {
	    		  divide();
			}
		});
		reset.addActionListener(new ActionListener() {    	
		      public void actionPerformed(ActionEvent e) {
		    		 
		    	 reset();
			}
		    			    
		});
		setSize(img.getWidth(null), img.getHeight(null));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
			       
			}
		
		       }
		    
		}

	void reset() {
		for (int i = 0; i < totalPieces-1; i++) {
			pieceNumber[i] = i;
			buttons[i].setText(i+"");
			buttons[15].setText(" ");
			buttons[15].setIcon(null);
		}
		
		int pieceWidth = img.getWidth(null) / pieces;
		int pieceHeight = img.getHeight(null) / pieces;
		int i=0;
	
	  for (int y = 0; y < pieces; y++) {
		    int sy = y * pieceWidth;	
			for (int x = 0; x < pieces; x++) {
		       int sx = x * pieceHeight;
			int number = pieceNumber[y* pieces + x];
			int dx = (number / pieces) * pieceHeight;
			int dy = (number % pieces) * pieceWidth;


		if(i!=15) {
			icon[pieceNumber[i]] = new ImageIcon(img.getSubimage(sx, sy, pieceWidth, pieceHeight)); 
			buttons[i].setIcon(icon[i]);
			i++;
			
		}
			}
		    }
			
	}
			
	void divide() {
		Random rand = new Random();
		for (int i = 0; i < totalPieces-1; i++) {
			buttons[i].setText(i+"");
			buttons[15].setText(" ");
			buttons[15].setIcon(null);
		}
		int[] ri = new int [15];
		
		for (int i = 0; i < totalPieces-1; i++) {
			
			ri[i] = rand.nextInt(totalPieces-1);
			for(int j=0;j<i;j++) {
				if(ri[j]==ri[i]) {
					ri[i]=rand.nextInt(totalPieces-1);			
			i--;
		
				}
			}
		
			pieceNumber[i]=ri[i];	
			System.out.print(pieceNumber[i]+" ");
				buttons[i].setIcon(icon[pieceNumber[i]]);
				
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		
		MyButton b = (MyButton) e.getSource();
		if( b.getText().equals(" ")==true) return;
		if( b.index == 0 ){
			if( buttons[1].getText().equals(" ") )
			{ buttons[1].setIcon(b.getIcon());buttons[1].setText(b.getText()); b.setText(" "); buttons[0].setIcon(null);} 
			if( buttons[4].getText().equals(" ") )
			{ buttons[4].setIcon(b.getIcon());buttons[4].setText(b.getText()); b.setText(" "); buttons[0].setIcon(null); } 
		}
		if( b.index == 1 ){
			if( buttons[0].getText().equals(" ") )
			{ buttons[0].setIcon(b.getIcon());buttons[0].setText(b.getText()); b.setText(" "); buttons[1].setIcon(null);} 
			if( buttons[2].getText().equals(" ") )
			{ buttons[2].setIcon(b.getIcon());buttons[2].setText(b.getText()); b.setText(" "); buttons[1].setIcon(null);} 
			if( buttons[5].getText().equals(" ") )
			{ buttons[5].setIcon(b.getIcon());buttons[5].setText(b.getText()); b.setText(" "); buttons[1].setIcon(null); } 
		}
		if( b.index == 2 ){
			if( buttons[1].getText().equals(" ") )
			{ buttons[1].setIcon(b.getIcon()); buttons[1].setText(b.getText());b.setText(" "); buttons[2].setIcon(null); } 
			if( buttons[3].getText().equals(" ") )
			{ buttons[3].setIcon(b.getIcon()); buttons[3].setText(b.getText());b.setText(" "); buttons[2].setIcon(null); } 
			if( buttons[6].getText().equals(" ") )
			{ buttons[6].setIcon(b.getIcon());buttons[6].setText(b.getText()); b.setText(" "); buttons[2].setIcon(null); } 
		}
		if( b.index == 3 ){
			if( buttons[2].getText().equals(" ") )
			{ buttons[2].setIcon(b.getIcon());buttons[2].setText(b.getText()); b.setText(" "); buttons[3].setIcon(null); } 
			if( buttons[7].getText().equals(" ") )
			{ buttons[7].setIcon(b.getIcon());buttons[7].setText(b.getText()); b.setText(" "); buttons[3].setIcon(null);} 
	
		}
		if( b.index == 4 ){
			if( buttons[0].getText().equals(" ") )
			{ buttons[0].setIcon(b.getIcon()); buttons[0].setText(b.getText());b.setText(" "); buttons[4].setIcon(null); } 
			if( buttons[5].getText().equals(" ") )
			{ buttons[5].setIcon(b.getIcon()); buttons[5].setText(b.getText());b.setText(" "); buttons[4].setIcon(null);} 
			if( buttons[8].getText().equals(" ") )
			{ buttons[8].setIcon(b.getIcon());buttons[8].setText(b.getText()); b.setText(" "); buttons[4].setIcon(null); } 
		}
		if( b.index == 5 ){
			if( buttons[1].getText().equals(" ") )
			{ buttons[1].setIcon(b.getIcon());buttons[1].setText(b.getText()); b.setText(" "); buttons[5].setIcon(null);} 
			if( buttons[4].getText().equals(" ") )
			{ buttons[4].setIcon(b.getIcon()); buttons[4].setText(b.getText());b.setText(" "); buttons[5].setIcon(null); } 
			if( buttons[6].getText().equals(" ") )
			{ buttons[6].setIcon(b.getIcon());buttons[6].setText(b.getText()); b.setText(" "); buttons[5].setIcon(null); } 
			if( buttons[9].getText().equals(" ") )
			{ buttons[9].setIcon(b.getIcon()); buttons[9].setText(b.getText());b.setText(" "); buttons[5].setIcon(null);} 
		}
		if( b.index == 6 ){
			if( buttons[2].getText().equals(" ") )
			{ buttons[2].setIcon(b.getIcon()); buttons[2].setText(b.getText());b.setText(" "); buttons[6].setIcon(null);} 
			if( buttons[5].getText().equals(" ") )
			{ buttons[5].setIcon(b.getIcon());buttons[5].setText(b.getText()); b.setText(" "); buttons[6].setIcon(null); } 
			if( buttons[7].getText().equals(" ") )
			{ buttons[7].setIcon(b.getIcon()); buttons[7].setText(b.getText());b.setText(" "); buttons[6].setIcon(null); } 
			if( buttons[10].getText().equals(" ") )
			{ buttons[10].setIcon(b.getIcon());buttons[10].setText(b.getText()); b.setText(" "); buttons[6].setIcon(null); } 
		}
		if( b.index == 7 ){
			if( buttons[3].getText().equals(" ") )
			{ buttons[3].setIcon(b.getIcon());buttons[3].setText(b.getText()); b.setText(" "); buttons[7].setIcon(null); } 
			if( buttons[6].getText().equals(" ") )
			{ buttons[6].setIcon(b.getIcon());buttons[6].setText(b.getText()); b.setText(" "); buttons[7].setIcon(null); } 
			if( buttons[11].getText().equals(" ") )
			{ buttons[11].setIcon(b.getIcon()); buttons[11].setText(b.getText());b.setText(" "); buttons[7].setIcon(null); } 
		}
		if( b.index == 8 ){
			if( buttons[4].getText().equals(" ") )
			{ buttons[4].setIcon(b.getIcon());buttons[4].setText(b.getText()); b.setText(" "); buttons[8].setIcon(null); } 
			if( buttons[9].getText().equals(" ") )
			{ buttons[9].setIcon(b.getIcon());buttons[9].setText(b.getText()); b.setText(" "); buttons[8].setIcon(null); } 
			if( buttons[12].getText().equals(" ") )
			{ buttons[12].setIcon(b.getIcon());buttons[12].setText(b.getText()); b.setText(" "); buttons[8].setIcon(null); } 
		}
		if( b.index == 9 ){
			if( buttons[5].getText().equals(" ") )
			{ buttons[5].setIcon(b.getIcon());buttons[5].setText(b.getText()); b.setText(" "); buttons[9].setIcon(null);} 
			if( buttons[8].getText().equals(" ") )
			{ buttons[8].setIcon(b.getIcon());buttons[8].setText(b.getText()); b.setText(" "); buttons[9].setIcon(null); } 
			if( buttons[10].getText().equals(" ") )
			{ buttons[10].setIcon(b.getIcon()); buttons[10].setText(b.getText());b.setText(" "); buttons[9].setIcon(null);} 
			if( buttons[13].getText().equals(" ") )
			{ buttons[13].setIcon(b.getIcon());buttons[13].setText(b.getText()); b.setText(" "); buttons[9].setIcon(null);} 
		}
		if( b.index == 10 ){
			if( buttons[6].getText().equals(" ") )
			{ buttons[6].setIcon(b.getIcon());buttons[6].setText(b.getText()); b.setText(" "); buttons[10].setIcon(null);} 
			if( buttons[9].getText().equals(" ") )
			{ buttons[9].setIcon(b.getIcon()); buttons[9].setText(b.getText());b.setText(" "); buttons[10].setIcon(null);} 
			if( buttons[11].getText().equals(" ") )
			{ buttons[11].setIcon(b.getIcon()); buttons[11].setText(b.getText());b.setText(" "); buttons[10].setIcon(null); } 
			if( buttons[14].getText().equals(" ") )
			{ buttons[14].setIcon(b.getIcon()); buttons[14].setText(b.getText());b.setText(" "); buttons[10].setIcon(null);} 
		}
		if( b.index == 11 ){
			if( buttons[7].getText().equals(" ") )
			{ buttons[7].setIcon(b.getIcon());buttons[7].setText(b.getText()); b.setText(" "); buttons[11].setIcon(null); } 
			if( buttons[10].getText().equals(" ") )
			{ buttons[10].setIcon(b.getIcon());buttons[10].setText(b.getText()); b.setText(" "); buttons[11].setIcon(null);} 
			if( buttons[15].getText().equals(" ") )
			{ buttons[15].setIcon(b.getIcon()); buttons[15].setText(b.getText());b.setText(" "); buttons[11].setIcon(null);} 
			
		}
		if( b.index == 12 ){
			if( buttons[8].getText().equals(" ") )
			{ buttons[8].setIcon(b.getIcon()); buttons[8].setText(b.getText());b.setText(" "); buttons[12].setIcon(null); } 	
			if( buttons[13].getText().equals(" ") )
			{ buttons[13].setIcon(b.getIcon());buttons[13].setText(b.getText()); b.setText(" "); buttons[12].setIcon(null);} 
		}
		if( b.index == 13 ){
			if( buttons[9].getText().equals(" ") )
			{ buttons[9].setIcon(b.getIcon()); buttons[9].setText(b.getText());b.setText(" "); buttons[13].setIcon(null); } 
			if( buttons[12].getText().equals(" ") )
			{ buttons[12].setIcon(b.getIcon()); buttons[12].setText(b.getText());b.setText(" "); buttons[13].setIcon(null);} 
			if( buttons[14].getText().equals(" ") )
			{ buttons[14].setIcon(b.getIcon());buttons[14].setText(b.getText()); b.setText(" "); buttons[13].setIcon(null);} 
		}
		if( b.index == 14 ){
			if( buttons[10].getText().equals(" ") )
			{ buttons[10].setIcon(b.getIcon()); buttons[10].setText(b.getText());b.setText(" "); buttons[14].setIcon(null); } 
			if( buttons[13].getText().equals(" ") )
			{ buttons[13].setIcon(b.getIcon()); buttons[13].setText(b.getText());b.setText(" "); buttons[14].setIcon(null); } 
			if( buttons[15].getText().equals(" ") )
			{ buttons[15].setIcon(b.getIcon());buttons[15].setText(b.getText()); b.setText(" "); buttons[14].setIcon(null);} 
		}
		if( b.index == 15 ){
			if( buttons[11].getText().equals(" ") )
			{ buttons[11].setIcon(b.getIcon());buttons[11].setText(b.getText());  b.setText(" "); buttons[15].setIcon(null);} 
			if( buttons[14].getText().equals(" ") )
			{ buttons[14].setIcon(b.getIcon()); buttons[14].setText(b.getText());b.setText(" "); buttons[15].setIcon(null); } 
		}

	}


    
	public static void main(String[] args) {
		new ImgPuzzle();
	}

}