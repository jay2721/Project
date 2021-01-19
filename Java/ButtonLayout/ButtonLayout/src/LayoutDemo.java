import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LayoutDemo extends JFrame{
	
	JTextField rowField;
	JTextField colField;
	int rows=0;
	int cols=0;
	Container cp= getContentPane();

	 GridBagLayout grid = new GridBagLayout();
     GridBagConstraints gbc = new GridBagConstraints();

	JPanel topPanel=new JPanel();
	JPanel btnPanel=new JPanel();
		
	public LayoutDemo() {

		 
		cp.setLayout(new FlowLayout());
		
		ButtonsListener listener = new ButtonsListener();
		
		rowField = new JTextField(10);
		colField = new JTextField(10);
		
		rowField.setEditable(true);
		colField.setEditable(true);
		
		JButton r_btn = new JButton("Input");
		JButton c_btn = new JButton("Input");
		
		r_btn.addActionListener(listener);
		c_btn.addActionListener(listener);
			
		topPanel.add(rowField);
		topPanel.add(r_btn);
		topPanel.add(colField);
		topPanel.add(c_btn);
		
	    topPanel.setPreferredSize(new Dimension (500,50));
	    btnPanel.setPreferredSize(new Dimension (500,200));
		cp.add(topPanel);
		cp.add(btnPanel,BorderLayout.CENTER);
		
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(500, 300);
	    setTitle("setLayout() Test");
	    setVisible(true);
	}
	
	 private class ButtonsListener implements ActionListener {
	      @Override
	      public void actionPerformed(ActionEvent e) {

				btnPanel.removeAll();
				int r_number=0;
				int c_number=0;

				try {
					r_number=Integer.parseInt(rowField.getText());
					c_number=Integer.parseInt(colField.getText());
					rows=r_number;
					cols=c_number;
					
			         int temp = rows;
			         rows = cols;
			         cols = temp;
					
					btnPanel.setLayout(grid); 
					
		            gbc.weightx = 0;
		            gbc.weighty = 0;
		               
		            gbc.insets = new Insets(5,5,5,5);
					
					JButton[] buttons = new JButton[r_number*c_number];	

					for(int i=0;i<buttons.length;i++) {
						buttons[i]=new JButton("Click [" + (i+1) + "]");
							layout(buttons[i],i/rows,i%rows,1,1);
							btnPanel.add(buttons[i]);	
					}
				
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Input the Numbers");
				}

				btnPanel.validate();
			}
	   }
	
	 public void layout(JComponent obj, int x, int y,int width, int height)
	 {
	  gbc.gridx=x; // 시작위치 x
	  gbc.gridy=y; // 시작위치 y
	  gbc.gridwidth=width; // 컨테이너 너비
	  gbc.gridheight=height;  // 컨테이너 높이
	  grid.setConstraints(obj,gbc);
	 }

	 
	   public static void main(String[] args) {
		      javax.swing.SwingUtilities.invokeLater(new Runnable() {
		         public void run() {
		            new LayoutDemo(); 
		         }
		      });
		   }
}