package com.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zelda.GamePanel;
import com.zelda.UtilityTool;

public class Entity {
    
    protected GamePanel gamePanel;
    protected int worldX, worldY;
    protected int speed;

    protected BufferedImage spriteUp1, spriteUp2, spriteUp3, spriteRight1, spriteRight2, spriteRight3, spriteDown1, spriteDown2, spriteDown3, spriteLeft1, spriteLeft2, spriteLeft3;
    protected String direction;

    protected int spriteCounter = 0;
    protected int spriteNumber = 1;
    protected int standCounter = 0;


    protected Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    protected int hitBoxDefaultX, hitBoxDefaultY;
    protected boolean collision = false;
    protected int actionLockCounter;

    public Entity(GamePanel gp){
        gamePanel = gp;
    }

    public void setAction(){}
    public void update(){
        setAction();
        collision = false;
        gamePanel.getCollisionChecker().checkTile(this);
        gamePanel.getCollisionChecker().checkObject(this, false);
        gamePanel.getCollisionChecker().checkPlayer(this);

        // Move npc if no collision
        if(!collision){
            switch(direction){
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 10){
            spriteCounter = 1;
            spriteNumber++;
            if      (spriteNumber > 3) spriteNumber = 1;
            else if (spriteNumber < 1) spriteNumber = 3;
        }
    }

    protected BufferedImage getSpriteSheet(String path){
        BufferedImage spriteSheet = null;
        try{
            spriteSheet = ImageIO.read(new File(path));
        }catch(IOException e){
            e.printStackTrace();
        }
        return spriteSheet;
    }

    protected void loadSprite(String path){
        UtilityTool uTool = new UtilityTool();
        BufferedImage spriteSheet = getSpriteSheet(path);
        
        int scaledWidth = spriteSheet.getWidth() * gamePanel.getScale();
        int scaledHeight = spriteSheet.getHeight() * gamePanel.getScale();
        spriteSheet = uTool.scaledImage(spriteSheet, scaledWidth, scaledHeight);
        int spriteSize = gamePanel.getTileSize();
        spriteDown2 = spriteSheet.getSubimage(0, 0, spriteSize, spriteSize);
        spriteDown1 = spriteSheet.getSubimage(16*3, 0, spriteSize, spriteSize);
        spriteDown3 = spriteSheet.getSubimage(32*3, 0, spriteSize, spriteSize);
        spriteLeft2 = spriteSheet.getSubimage(0, 16*3, spriteSize, spriteSize);
        spriteLeft1 = spriteSheet.getSubimage(16*3, 16*3, spriteSize, spriteSize);
        spriteLeft3 = spriteSheet.getSubimage(32*3, 16*3, spriteSize, spriteSize);
        spriteRight2 = spriteSheet.getSubimage(0, 32*3, spriteSize, spriteSize);
        spriteRight1 = spriteSheet.getSubimage(16*3, 32*3, spriteSize, spriteSize);
        spriteRight3 = spriteSheet.getSubimage(32*3, 32*3, spriteSize, spriteSize);
        spriteUp2 = spriteSheet.getSubimage(0, 48*3, spriteSize, spriteSize);
        spriteUp1 = spriteSheet.getSubimage(16*3, 48*3, spriteSize, spriteSize);
        spriteUp3 = spriteSheet.getSubimage(32*3, 48*3, spriteSize, spriteSize);
    }

    public void draw(Graphics2D g2){
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX()
        && worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX()
        && worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY()
        && worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()){
            BufferedImage sprite = null;
            switch(direction){
            case "up":
                if(spriteNumber == 1)
                    sprite = spriteUp1;
                else if(spriteNumber == 2)
                    sprite = spriteUp2;
                else if(spriteNumber == 3)
                    sprite = spriteUp3;
                break;
            case "down":
                if(spriteNumber == 1)
                    sprite = spriteDown1;
                else if(spriteNumber == 2)
                    sprite = spriteDown2;
                else if(spriteNumber == 3)
                    sprite = spriteDown3;
                break;
            case "left":
                if(spriteNumber == 1)
                    sprite = spriteLeft1;
                else if(spriteNumber == 2)
                    sprite = spriteLeft2;
                else if(spriteNumber == 3)
                    sprite = spriteLeft3;
                break;
            case "right":
                if(spriteNumber == 1)
                    sprite = spriteRight1;
                else if(spriteNumber == 2)
                    sprite = spriteRight2;
                else if(spriteNumber == 3)
                    sprite = spriteRight3;
                break;
            }
            g2.drawImage(sprite, screenX, screenY, null);
        }
    }

    public int getWorldX() {return worldX;}
    public void setWorldX(int _worldX) {worldX = _worldX;}

    public int getWorldY() {return worldY;}
    public void setWorldY(int _worldY) {worldY = _worldY;}

    public int getSpeed() {return speed;}
    public Rectangle getHitBox() {return hitBox;}
    public int getHitBoxDefaultX() {return hitBoxDefaultX;}
    public int getHitBoxDefaultY() {return hitBoxDefaultY;}
    public boolean getCollision() {return collision;}
    public void setCollision(boolean _collision) {collision = _collision;}
    public BufferedImage getSpriteUp1() {return spriteUp1;}
    public BufferedImage getSpriteUp2() {return spriteUp2;}
    public BufferedImage getSpriteUp3() {return spriteUp3;}
    public BufferedImage getSpriteDown1() {return spriteDown1;}
    public BufferedImage getSpriteDown2() {return spriteDown2;}
    public BufferedImage getSpriteDown3() {return spriteDown3;}
    public BufferedImage getSpriteLeft1() {return spriteLeft1;}
    public BufferedImage getSpriteLeft2() {return spriteLeft2;}
    public BufferedImage getSpriteLeft3() {return spriteLeft3;}
    public BufferedImage getSpriteRight1() {return spriteRight1;}
    public BufferedImage getSpriteRight2() {return spriteRight2;}
    public BufferedImage getSpriteRight3() {return spriteRight3;}
    public String getDirection() {return direction;}
}
