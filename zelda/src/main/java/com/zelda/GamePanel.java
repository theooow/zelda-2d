package com.zelda;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class GamePanel extends JPanel {
    //Screen settings
    final int originalTileSize = 16; //16x16 pixels
    final int scale = 3; //16x16 pixels * 3 = 48x48 pixels

    final int tileSize = originalTileSize * scale;

    final int maxScreencol = 16;
    final int maxScreenrow = 12;
    final int screenWidth = tileSize * maxScreencol; //768 pixels
    final int screenHeight = tileSize * maxScreenrow; //576 pixels

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }
}
