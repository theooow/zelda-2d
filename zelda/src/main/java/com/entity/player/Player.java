package com.entity.player;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.entity.Entity;
import com.object.OBJ_Chest;
import com.zelda.GamePanel;
import com.zelda.KeyHandler;

public class Player extends Entity{
    
    GamePanel gamePanel;
    KeyHandler keyHandler;

    final int screenX, screenY;
    int nbKeys;

    public Player(GamePanel _gamePanel, KeyHandler _keyHandler){
        gamePanel = _gamePanel;
        keyHandler = _keyHandler;
        screenX = gamePanel.getScreenWidth()/2 - gamePanel.getTileSize()/2;
        screenY = gamePanel.getScreenHeight()/2 - gamePanel.getTileSize()/2;
        hitBox = new Rectangle(16, 24, 16, 20);
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        setDefaultValues();
        loadSprite();
    }

    private void setDefaultValues(){
        worldX = gamePanel.getTileSize()*25;
        worldY = gamePanel.getTileSize()*25;
        speed = 4;
        direction = "down";
    }

    public void loadSprite(){
        BufferedImage spriteSheet = getSpriteSheet();
        int spriteSize = gamePanel.getOriginalTileSize();
        spriteDown2 = spriteSheet.getSubimage(0, 0, spriteSize, spriteSize);
        spriteDown1 = spriteSheet.getSubimage(16, 0, spriteSize, spriteSize);
        spriteDown3 = spriteSheet.getSubimage(32, 0, spriteSize, spriteSize);
        spriteLeft2 = spriteSheet.getSubimage(0, 16, spriteSize, spriteSize);
        spriteLeft1 = spriteSheet.getSubimage(16, 16, spriteSize, spriteSize);
        spriteLeft3 = spriteSheet.getSubimage(32, 16, spriteSize, spriteSize);
        spriteRight2 = spriteSheet.getSubimage(0, 32, spriteSize, spriteSize);
        spriteRight1 = spriteSheet.getSubimage(16, 32, spriteSize, spriteSize);
        spriteRight3 = spriteSheet.getSubimage(32, 32, spriteSize, spriteSize);
        spriteUp2 = spriteSheet.getSubimage(0, 48, spriteSize, spriteSize);
        spriteUp1 = spriteSheet.getSubimage(16, 48, spriteSize, spriteSize);
        spriteUp3 = spriteSheet.getSubimage(32, 48, spriteSize, spriteSize);
    }

    public void update(){

        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){

            if(keyHandler.shiftPressed) speed = 6;
            else                        speed = 4;
            if(keyHandler.upPressed){
                direction = "up";
            } else if(keyHandler.downPressed){
                direction = "down";
            } else if(keyHandler.leftPressed){
                direction = "left";
            } else if(keyHandler.rightPressed){
                direction = "right";
            }

            // Collision check
            collision = false;
            gamePanel.getCollisionChecker().checkTile(this);

            // Collision check with objects
            int objIndex = gamePanel.getCollisionChecker().checkObject(this, true);
            pickUpItem(objIndex);

            // Move player if no collision
            if(!collision){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 10){
                spriteCounter = 1;
                spriteNumber++;
                if      (spriteNumber > 3) spriteNumber = 1;
                else if (spriteNumber < 1) spriteNumber = 3;
            }
        }else{
            spriteNumber = 1;
            spriteCounter = 0;
        }
    }

    public void pickUpItem(int objIndex){
        if(objIndex != 999){
            String objName = gamePanel.getObj(objIndex).getName();
            switch(objName){
                case "key":
                    gamePanel.setObj(objIndex, null);
                    gamePanel.playSE(1);
                    nbKeys++;
                    gamePanel.getMyUI().showMessage("You found a key !");
                    break;
                case "chest":
                        if(gamePanel.getObj(objIndex) instanceof OBJ_Chest){
                            OBJ_Chest o = (OBJ_Chest)gamePanel.getObj(objIndex);
                            if(nbKeys > 0){
                                if(direction == "up" || direction == "left" || direction == "right"){
                                    o.open();   
                                    gamePanel.playSE(3);
                                    nbKeys--;
                                }else
                                    gamePanel.getMyUI().showMessage("You can't open the chest from the back !");
                            }else
                                if(!o.getState())
                                    gamePanel.getMyUI().showMessage("You need a key !"); 
                        }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){
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
        g2.drawImage(sprite, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    // Getters
    public int getWorldX(){ return worldX; }
    public int getWorldY(){ return worldY; }
    public int getScreenX(){ return screenX; }
    public int getScreenY(){ return screenY; }
    public int getSpeed(){ return speed; }
    public String getDirection(){ return direction; }
    public int getNbKeys(){ return nbKeys; }
}
