package pro;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

class MouseHandler implements MouseListener {
		ImgPanel _canvas;
		
		public MouseHandler(ImgPanel canvas)
		{
			_canvas = canvas;			
		}
	
        public void mouseEntered (MouseEvent e) {

        }

        public void mouseExited (MouseEvent e) {

        }

        public void mousePressed (MouseEvent e) {

        }
        public void mouseClicked (MouseEvent e) {
        	int x = e.getX();
        	int y =e.getY();
        	_canvas.clicked(x, y);
        }
        public void mouseReleased (MouseEvent e) {

        }
    }