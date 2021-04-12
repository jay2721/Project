package pro;

import javax.swing.*;        
import java.awt.event.*; 

class LoadActionListener implements ActionListener {
    JTextField text;
    ImgPanel imagePanel;
    LoadActionListener(JTextField text, 
                       ImgPanel imagePanel) { 
        this.text = text;
        this.imagePanel = imagePanel;
    }
    public void actionPerformed(ActionEvent e) {
        //imagePanel.setPath(text.getText());
        imagePanel.repaint();
    }
}
