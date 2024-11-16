package org.example;
import javax.swing.JFrame;
import java.awt.*;

public class Frame extends JFrame {


    public  Frame(){
        add(new Panel());
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main (String[] args) {
        new Frame();
    }

}
