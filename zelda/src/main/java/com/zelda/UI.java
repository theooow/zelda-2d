package com.zelda;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import com.object.OBJ_Key;

public class UI {
    
    GamePanel gamePanel;
    Font font;

    BufferedImage keyImage;

    boolean messageOnScreen = false;
    String message = "";
    int messageTimer = 0;

    double timer = 0;
    DecimalFormat df = new DecimalFormat("#0.00");

    public UI(GamePanel _gamePanel){
        gamePanel = _gamePanel;
        font = new Font("Arial", Font.BOLD, 42);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.getImage();
    }

    public void showMessage(String _message){
        message = _message;
        messageOnScreen = true;
    }

    public void draw(Graphics2D g2){
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, 10, 10, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        g2.drawString("x" + gamePanel.getPlayer().getNbKeys(), 60, 50);

        timer += (double)1/60;
        g2.drawString("Time: " + df.format(timer), gamePanel.getTileSize()*11, 50);

        if(messageOnScreen){
            g2.setFont(font.deriveFont(30F));
            g2.drawString(message, gamePanel.getTileSize()/2, gamePanel.getTileSize()*5);
            messageTimer++;

            if(messageTimer > 120){
                messageOnScreen = false;
                messageTimer = 0;
            }
        }
    }
}
