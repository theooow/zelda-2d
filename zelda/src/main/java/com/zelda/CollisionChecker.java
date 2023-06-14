package com.zelda;

import com.entity.Entity;
import com.map.LayerMap;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel _gamePanel){
        gamePanel = _gamePanel;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.getWorldX() + entity.getHitBox().x;
        int entityRightWorldX = entity.getWorldX() + entity.getHitBox().x + entity.getHitBox().width;
        int entityTopWorldY = entity.getWorldY() + entity.getHitBox().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getHitBox().y + entity.getHitBox().height;

        int entityLeftCol = entityLeftWorldX / gamePanel.getTileSize();
        int entityRightCol = entityRightWorldX / gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY / gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY / gamePanel.getTileSize();

        int tileNum1, tileNum2;

        for(LayerMap layer : gamePanel.getParser().getLayers()){
            if(layer.getName().contains("collision")){

                switch(entity.getDirection()){
                    case "up":
                        entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.getTileSize();
                        tileNum1 = layer.getMap()[entityLeftCol][entityTopRow];
                        tileNum2 = layer.getMap()[entityRightCol][entityTopRow];
                        if(gamePanel.getTileManager().getTile().get(tileNum1).getIsSolid() || gamePanel.getTileManager().getTile().get(tileNum2).getIsSolid()){
                            entity.setCollision(true);
                        }
                        break;
                    case "down":
                        entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.getTileSize();
                        tileNum1 = layer.getMap()[entityLeftCol][entityBottomRow];
                        tileNum2 = layer.getMap()[entityRightCol][entityBottomRow];
                        if(gamePanel.getTileManager().getTile().get(tileNum1).getIsSolid() || gamePanel.getTileManager().getTile().get(tileNum2).getIsSolid()){
                            entity.setCollision(true);
                        }
                        break;
                    case "left":
                        entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gamePanel.getTileSize();
                        tileNum1 = layer.getMap()[entityLeftCol][entityTopRow];
                        tileNum2 = layer.getMap()[entityLeftCol][entityBottomRow];
                        if(gamePanel.getTileManager().getTile().get(tileNum1).getIsSolid() || gamePanel.getTileManager().getTile().get(tileNum2).getIsSolid()){
                            entity.setCollision(true);
                        }
                        break;
                    case "right":
                        entityRightCol = (entityRightWorldX + entity.getSpeed()) / gamePanel.getTileSize();
                        tileNum1 = layer.getMap()[entityRightCol][entityBottomRow];
                        tileNum2 = layer.getMap()[entityRightCol][entityTopRow];
                        if(gamePanel.getTileManager().getTile().get(tileNum1).getIsSolid() || gamePanel.getTileManager().getTile().get(tileNum2).getIsSolid()){
                            entity.setCollision(true);
                        }
                        break;
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;
        return index;
    }
}
