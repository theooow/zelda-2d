package com.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.zelda.GamePanel;

public class SuperObject {
    
    private BufferedImage image;
    private String name;
    private boolean isSolid = false;
    private int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gamePanel){
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX()
        && worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX()
        && worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY()
        && worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY())
            g2.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    // geters

    public BufferedImage getImage(){return image;}
    public String getName(){return name;}
    public boolean getIsSolid(){return isSolid;}
    public int getWorldX(){return worldX;}
    public int getWorldY(){return worldY;}

    // seters

    public void setImage(BufferedImage _image){image = _image;}
    public void setName(String _name){name = _name;}
    public void setIsSolid(boolean _isSolid){isSolid = _isSolid;}
    public void setWorldX(int _worldX){worldX = _worldX;}
    public void setWorldY(int _worldY){worldY = _worldY;}

}
