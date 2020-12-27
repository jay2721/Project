import java.awt.*;
import java.awt.event.*;

public class MouseMotionDemo extends Frame implements MouseMotionListener{
	   private TextField tfMouseX; 
	   private TextField tfMouseY; 
	   private TextField poMouseX;
	   private TextField poMouseY;

	   public MouseMotionDemo() {
	      setLayout(new FlowLayout()); 
	 
	      add(new Label("X-Click: ")); 
	 
	      tfMouseX = new TextField(10); 
	      tfMouseX.setEditable(false);  
	      add(tfMouseX);                
	 
	      add(new Label("Y-Click: "));
	 
	      tfMouseY = new TextField(10);
	      tfMouseY.setEditable(false);  
	      add(tfMouseY);                
   
	      add(new Label("X-Position: "));
	      
	      poMouseX=new TextField(10);
	      poMouseX.setEditable(false);
	      add(poMouseX);
	      
	      add(new Label("Y-position: "));
	      
	      poMouseY=new TextField(10);
	      poMouseY.setEditable(false);
	      add(poMouseY);
	      
	      addMouseMotionListener(this);
	             
	      setTitle("MouseMotion Demo"); 
	      setSize(400, 150);           
	      setVisible(true);  
	      
	     this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
	     });	   
   }
 
   public static void main(String[] args) {
      new MouseMotionDemo(); 
   }
 
   @Override
   public void mouseDragged(MouseEvent e) {
      tfMouseX.setText(e.getX() + "");
      tfMouseY.setText(e.getY() + "");
   }

   @Override
   public void mouseMoved(MouseEvent e) {
	   poMouseX.setText(e.getX()+"");
	   poMouseY.setText(e.getY()+"");
   }  
}