package org.rockislandschools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayStatus extends JFrame {
    
    public void buildFrame(String ipAdd, String status){
    
    JFrame frame = new JFrame("Host Status");
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        
    JPanel panel = new JPanel();
        
    JLabel ipLabel = new JLabel(ipAdd);
    ipLabel.setOpaque(true);
    ipLabel.setBackground(Color.white);
    ipLabel.setPreferredSize(new Dimension(20, 25));
        
    JLabel stateLabel = new JLabel(status);
    stateLabel.setOpaque(true);
    stateLabel.setBackground(Color.yellow);        
    stateLabel.setPreferredSize(new Dimension(20, 25));
        
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    panel.setPreferredSize(new Dimension(400,400));
    frame.getContentPane().add(panel);
    frame.getContentPane().add(ipLabel, BorderLayout.CENTER);
    frame.getContentPane().add(stateLabel, BorderLayout.AFTER_LAST_LINE);
    frame.pack();
    frame.setVisible(true);
    
    }
}

