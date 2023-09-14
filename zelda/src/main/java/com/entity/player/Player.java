package com.entity.player;

import java.awt.Rectangle;

import com.entity.Entity;
import com.object.OBJ_Chest;
import com.zelda.GamePanel;
import com.zelda.KeyHandler;

public class Player extends Entity{
    
    KeyHandler keyHandler;

    int screenX, screenY;
    int nbKeys;

    public Player(GamePanel _gamePanel, KeyHandler _keyHandler){
        super(_gamePanel);
        keyHandler = _keyHandler;
        screenX = gamePanel.getScreenWidth()/2 - gamePanel.getTileSize()/2;
        screenY = gamePanel.getScreenHeight()/2 - gamePanel.getTileSize()/2;
        hitBox = new Rectangle(16, 20, 16, 28);
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        setDefaultValues();
        loadSprite("./zelda/src/main/java/res/players/char.png");
    }

    private void setDefaultValues(){
        //spawnlocation
        worldX = gamePanel.getTileSize()*25;
        worldY = gamePanel.getTileSize()*25;

        speed = 4;
        direction = "down";

        // player status
        maxLife = 6;
        life = maxLife;
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

            // Collision check with NPCs
            int npcIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpc());
            interactNPC(npcIndex);

            // Check event
            gamePanel.getEventHandler().checkEvent();

            gamePanel.getKeyHandler().spacePressed = false;


            
            //System.out.println("2/ " + gamePanel.getScreenWidth() + " " + gamePanel.getScreenHeight());


            // Move player if no collision
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
        }else{
            standCounter++;
            if(standCounter > 20){
                spriteNumber = 1;
                standCounter = 0;
            }
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

    public void interactNPC(int i){
        if(i != 999){
            if(gamePanel.getKeyHandler().spacePressed){
                gamePanel.gameState = gamePanel.DIALOG_STATE;
                gamePanel.getNpc(i).speak();
            }
        }
    }

    // Getters
    public int getWorldX(){ return worldX; }
    public int getWorldY(){ return worldY; }
    public int getScreenX(){ return screenX; }
    public int getScreenY(){ return screenY; }
    public int getSpeed(){ return speed; }
    public String getDirection(){ return direction; }
    public int getNbKeys(){ return nbKeys; }

    public int getMaxLife() {
        return maxLife;
    }
    public int getLife(){
        return life;
    }

    public int getHitBoxDefaultX(){ return hitBoxDefaultX;}
    public int getHitBoxDefaultY(){ return hitBoxDefaultY;}


    // Setters
    public void setLife(int i) {
        life += i;
    }
    public void setScreenX(int _screenX){ screenX = _screenX; }
    public void setScreenY(int _screenY){ screenY = _screenY; }
}
