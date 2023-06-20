package com.zelda;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.object.OBJ_Key;

public class UI {
    
    GamePanel gamePanel;
    Graphics2D g2;
    Font font, dialogFont;

    BufferedImage keyImage;

    boolean messageOnScreen = false;
    String message = "";
    int messageTimer = 0;

    String currentDialog = "";

    public UI(GamePanel _gamePanel){
        gamePanel = _gamePanel;
        font = new Font("Arial", Font.BOLD, 42);
        font = new Font("Arial", Font.PLAIN, 24);
        OBJ_Key key = new OBJ_Key(_gamePanel);
        keyImage = key.getImage();
    }

    public void showMessage(String _message){
        message = _message;
        messageOnScreen = true;
    }

    public void draw(Graphics2D _g2){
        g2 = _g2;
        g2.setFont(font);
        g2.setColor(Color.WHITE);

        if(gamePanel.gameState == gamePanel.PAUSE_STATE){
            int lenght = (int)g2.getFontMetrics().getStringBounds("PAUSE", g2).getWidth();
            int x = (gamePanel.getWidth() - lenght)/2;
            int y = gamePanel.getScreenHeight()/2;
            g2.drawString("PAUSE", x, y);
        }else if(gamePanel.gameState == gamePanel.DIALOG_STATE){
            drawDialogScreen();
        }else if(gamePanel.gameState == gamePanel.PLAY_STATE){

            g2.drawImage(keyImage, 10, 10, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            g2.setFont(font);
            g2.drawString("x" + gamePanel.getPlayer().getNbKeys(), 60, 50);

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

    private void drawDialogScreen(){
        //Window
        int x = gamePanel.getTileSize();
        int y = gamePanel.getTileSize();
        int width = gamePanel.getScreenWidth() - gamePanel.getTileSize()*2;
        int height = gamePanel.getTileSize()*4;
        drawSubWindow(x, y, width, height);

        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();

        g2.setFont(dialogFont);
        for(String line : currentDialog.split("\n")){
            g2.drawString(line, x, y);
            y+=40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void setCurrentDialog(String s){currentDialog = s;}
}
