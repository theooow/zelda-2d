package com.zelda;

import com.entity.Entity;

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

        switch(entity.getDirection()){
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum(entityLeftCol, entityTopRow);
                tileNum2 = gamePanel.getTileManager().getMapTileNum(entityRightCol, entityTopRow);
                if(gamePanel.getTileManager().getTile().get(tileNum1).getIsSolid() || gamePanel.getTileManager().getTile().get(tileNum2).getIsSolid()){
                    entity.setCollision(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum(entityLeftCol, entityBottomRow);
                tileNum2 = gamePanel.getTileManager().getMapTileNum(entityRightCol, entityBottomRow);
                if(gamePanel.getTileManager().getTile().get(tileNum1).getIsSolid() || gamePanel.getTileManager().getTile().get(tileNum2).getIsSolid()){
                    entity.setCollision(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum(entityLeftCol, entityTopRow);
                tileNum2 = gamePanel.getTileManager().getMapTileNum(entityLeftCol, entityBottomRow);
                if(gamePanel.getTileManager().getTile().get(tileNum1).getIsSolid() || gamePanel.getTileManager().getTile().get(tileNum2).getIsSolid()){
                    entity.setCollision(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum(entityRightCol, entityBottomRow);
                tileNum2 = gamePanel.getTileManager().getMapTileNum(entityRightCol, entityTopRow);
                if(gamePanel.getTileManager().getTile().get(tileNum1).getIsSolid() || gamePanel.getTileManager().getTile().get(tileNum2).getIsSolid()){
                    entity.setCollision(true);
                }
                break;
        }
    }
}
