package model;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
        
public class Win extends JFrame{
    public Win (int x, int y){
        setupFrame(x, y);
        setupComponents();
        setVisible(true);
    }
    
    public Win (){
    this(-1, -1);
    }
    
    private void setupFrame(int x, int y){
        setTitle("Título");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        if ( x == -1 & y == -1)
            setLocation(200, 400);
        else
            setLocation(x, y);
    }
    
    private void setupComponents(){
        JLabel labelJava = new JLabel("Java");
        labelJava.setToolTipText("Write once, run anywhere");
        Icon iconJava = new ImageIcon(getClass().getResource("testeIocn.jpg"));
        
        labelJava.setIcon(iconJava);
        labelJava.setHorizontalTextPosition(JLabel.CENTER);
        labelJava.setVerticalTextPosition(JLabel.CENTER);
        labelJava.setLocation(10, 10);
        labelJava.setSize(labelJava.getPreferredSize());
        add(labelJava);
    }
}
