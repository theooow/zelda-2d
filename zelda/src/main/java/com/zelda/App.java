package com.zelda;

import javax.swing.JFrame;

public class App 
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Zelda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();

    }
}
