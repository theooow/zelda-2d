package com.zelda;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import com.object.OBJ_Heart;
import com.object.OBJ_Key;
public class UI {
    
    GamePanel gamePanel;
    Graphics2D g2;
    Font maruMonica;

    BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank;

    boolean messageOnScreen = false;
    String message = "";
    int messageTimer = 0;

    String currentDialog = "";
    int commandNum = 0;

    public UI(GamePanel _gamePanel){
        gamePanel = _gamePanel;
        try {
            InputStream is = getClass().getResourceAsStream("/res/fonts/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OBJ_Key key = new OBJ_Key(gamePanel);
        keyImage = key.getImage();

        OBJ_Heart heart = new OBJ_Heart(_gamePanel);
        heart_full = heart.getImage();
        heart.setHalf();
        heart_half = heart.getImage();
        heart.setEmpty();
        heart_blank = heart.getImage();
    }

    public void showMessage(String _message){
        message = _message;
        messageOnScreen = true;
    }

    public void draw(Graphics2D _g2){
        g2 = _g2;
        g2.setFont(maruMonica);
        g2.setColor(Color.WHITE);

        if(gamePanel.gameState == gamePanel.TITLE_STATE)
            drawTitleScreen();
        else if(gamePanel.gameState == gamePanel.PAUSE_STATE){
            drawPlayerLife();
            drawPauseScreen();
        }
        else if(gamePanel.gameState == gamePanel.DIALOG_STATE){
            drawPlayerLife();
            drawDialogScreen();
        }
        else if(gamePanel.gameState == gamePanel.PLAY_STATE){
            drawPlayerLife();
            drawUI();
        }else if(gamePanel.gameState == gamePanel.OPTION_STATE){ // modify this line
            drawOption();
        }else if(gamePanel.gameState == gamePanel.OPTIONVIDEO_STATE){ // modify this line
            drawOptionVideo();
        }
        
    }

    private void drawPlayerLife() {
        int x = gamePanel.getTileSize()/2;
        int y = gamePanel.getTileSize()/2;
        int i = 0;

        while(i < gamePanel.getPlayer().getMaxLife()/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x+= gamePanel.getTileSize();
        }

        x = gamePanel.getTileSize()/2;
        y = gamePanel.getTileSize()/2;
        i = 0;

        while(i < gamePanel.getPlayer().getLife()){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gamePanel.getPlayer().getLife())
                g2.drawImage(heart_full, x, y, null);
            i++;
            x += gamePanel.getTileSize();
        }
    }

    private void drawTitleScreen() {
        //Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        //Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));
        String text = "Like The Legend Of Zelda";
        int x = getXforCenteredText(text);
        int y = gamePanel.getTileSize()*4;
            //Shadow
            g2.setColor(Color.GRAY);
            g2.drawString(text, x-2, y+8);
            //Text
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
        //Image
        BufferedImage i = gamePanel.getPlayer().getSpriteDown1();
        x = gamePanel.getScreenWidth()/2 - gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        g2.drawImage(i, x, y, gamePanel.getTileSize()*2, gamePanel.getTileSize()*2, null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize()*4;
        g2.drawString(text, x, y);
        if(commandNum==0)
            g2.drawString(">", x-gamePanel.getTileSize(), y);
        
        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==1)
            g2.drawString(">", x-gamePanel.getTileSize(), y);

        text = "OPTIONS";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==2)
            g2.drawString(">", x-gamePanel.getTileSize(), y);
        
        text = "LEAVE";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==3)
            g2.drawString(">", x-gamePanel.getTileSize(), y);
        
    }

    private void drawOption()
    {
        
        //Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        //Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));
        String text = "Like The Legend Of Zelda";
        int x = getXforCenteredText(text);
        int y = gamePanel.getTileSize()*2;
            //Shadow
            g2.setColor(Color.GRAY);
            g2.drawString(text, x-2, y+8);
            //Text
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
        text = "Options";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
            //shadow of the second title
            g2.setColor(Color.GRAY);
            g2.drawString(text, x-2, y+8);
            //second title
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

        text = "Video";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize()*2;
        g2.drawString(text, x, y);
        if(commandNum==0)
            g2.drawString(">", x-gamePanel.getTileSize(), y);  

        text = "Sound";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==1)
            g2.drawString(">", x-gamePanel.getTileSize(), y);  
        
        text = "Resume To Game";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==2)
            g2.drawString(">", x-gamePanel.getTileSize(), y);

        
        text = "Back";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==3)
            g2.drawString(">", x-gamePanel.getTileSize(), y);  



    }

    private void drawOptionVideo()
    {
        //Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        //Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));
        String text = "Like The Legend Of Zelda";
        int x = getXforCenteredText(text);
        int y = gamePanel.getTileSize()*2;
            //Shadow
            g2.setColor(Color.GRAY);
            g2.drawString(text, x-2, y+8);
            //Text
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
        text = "Options - Video";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
            //shadow of the second title
            g2.setColor(Color.GRAY);
            g2.drawString(text, x-2, y+8);
            //second title
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
        
        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

        text = "800x600"; // 16x12
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize()*2;
        g2.drawString(text, x, y);
        if(commandNum==0)
            g2.drawString(">", x-gamePanel.getTileSize(), y);

        text = "1280x720"; // 26x15
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==1)
            g2.drawString(">", x-gamePanel.getTileSize(), y);

        text = "1600x900"; // 32x18
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==2)
            g2.drawString(">", x-gamePanel.getTileSize(), y);
        
        text = "1920x1080"; // 38x21
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==3)
            g2.drawString(">", x-gamePanel.getTileSize(), y);
        
        text = "Back";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==4)
            g2.drawString(">", x-gamePanel.getTileSize(), y);
        

    }

    private void drawUI() {
        g2.drawImage(keyImage, gamePanel.getScreenWidth()-gamePanel.getTileSize()*2, 10, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        g2.drawString("x" + gamePanel.getPlayer().getNbKeys(), gamePanel.getScreenWidth()-gamePanel.getTileSize(), 55);

        if(messageOnScreen){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gamePanel.getTileSize()/2, gamePanel.getTileSize()*5);
            messageTimer++;

            if(messageTimer > 120){
                messageOnScreen = false;
                messageTimer = 0;
            }
        }
    }
    private void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        int x = getXforCenteredText("PAUSE");
        int y = gamePanel.getScreenHeight()/4;
        g2.drawString("PAUSE", x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        String text = "Save";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize()*4;
        g2.drawString(text, x, y);
        if(commandNum==0)
            g2.drawString(">", x-gamePanel.getTileSize(), y); //! not working for now
        
        text = "Options";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==1)
            g2.drawString(">", x-gamePanel.getTileSize(), y); //! not working for now

        text = "Resume";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==2)
            g2.drawString(">", x-gamePanel.getTileSize(), y); //! not working for now
        
        text = "Quit";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if(commandNum==3)
            g2.drawString(">", x-gamePanel.getTileSize(), y); //! not working for now
        
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

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
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

    private int getXforCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.screenWidth/2 - lenght/2;
    }

    // Setters
    public void setCurrentDialog(String s){currentDialog = s;}
}
