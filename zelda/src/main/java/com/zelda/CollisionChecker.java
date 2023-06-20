package com.zelda;

import com.entity.Entity;
import com.entity.player.Player;
import com.map.LayerMap;
import com.object.SuperObject;

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
        int counter = 0;
        for(SuperObject o : gamePanel.getObj()){
            if(o != null){
                //Get entity's hitbox position
                entity.getHitBox().x = entity.getWorldX() + entity.getHitBox().x;
                entity.getHitBox().y = entity.getWorldY() + entity.getHitBox().y;

                //Get object's hitbox position
                o.getHitBox().x = o.getWorldX() + o.getHitBox().x;
                o.getHitBox().y = o.getWorldY() + o.getHitBox().y;

                //Check collision
                switch(entity.getDirection()){
                    case "up":
                        entity.getHitBox().y -= entity.getSpeed();
                        if(entity.getHitBox().intersects(o.getHitBox())){
                            if(o.getIsSolid())
                                entity.setCollision(true);
                            if(player)
                                index = counter;
                        }                      
                        break;
                    case "down":
                        entity.getHitBox().y += entity.getSpeed();
                        if(entity.getHitBox().intersects(o.getHitBox())){
                            if(o.getIsSolid())
                                entity.setCollision(true);
                            if(player)
                                index = counter;
                        }                 
                        break;
                    case "left":
                        entity.getHitBox().x -= entity.getSpeed();
                        if(entity.getHitBox().intersects(o.getHitBox())){
                            if(o.getIsSolid())
                                entity.setCollision(true);
                            if(player)
                                index = counter;
                        }                      
                        break;
                    case "right":
                        entity.getHitBox().x += entity.getSpeed();
                        if(entity.getHitBox().intersects(o.getHitBox())){
                            if(o.getIsSolid())
                                entity.setCollision(true);
                            if(player)
                                index = counter;
                        }  
                        break;
                }
                //Reset entity's hitbox position
                entity.getHitBox().x = entity.getHitBoxDefaultX();
                entity.getHitBox().y = entity.getHitBoxDefaultY();

                //Reset object's hitbox position
                o.getHitBox().x = o.getHitBoxDefaultX();
                o.getHitBox().y = o.getHitBoxDefaultY();
            }
            counter++;
        }
        return index;
    }

    //NPCs or Monsters collisions
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        int counter = 0;
        for(Entity e : target){
            if(e != null){
                //Get entity's hitbox position
                entity.getHitBox().x = entity.getWorldX() + entity.getHitBox().x;
                entity.getHitBox().y = entity.getWorldY() + entity.getHitBox().y;

                //Get object's hitbox position
                e.getHitBox().x = e.getWorldX() + e.getHitBox().x;
                e.getHitBox().y = e.getWorldY() + e.getHitBox().y;

                //Check collision
                switch(entity.getDirection()){
                    case "up":
                        entity.getHitBox().y -= entity.getSpeed();
                        if(entity.getHitBox().intersects(e.getHitBox())){
                            entity.setCollision(true);
                            index = counter;
                        }                      
                        break;
                    case "down":
                        entity.getHitBox().y += entity.getSpeed();
                        if(entity.getHitBox().intersects(e.getHitBox())){
                            entity.setCollision(true);
                            index = counter;
                        }                 
                        break;
                    case "left":
                        entity.getHitBox().x -= entity.getSpeed();
                        if(entity.getHitBox().intersects(e.getHitBox())){
                            entity.setCollision(true);
                            index = counter;
                        }                      
                        break;
                    case "right":
                        entity.getHitBox().x += entity.getSpeed();
                        if(entity.getHitBox().intersects(e.getHitBox())){
                            entity.setCollision(true);
                            index = counter;
                        }  
                        break;
                }
                //Reset entity's hitbox position
                entity.getHitBox().x = entity.getHitBoxDefaultX();
                entity.getHitBox().y = entity.getHitBoxDefaultY();

                //Reset object's hitbox position
                e.getHitBox().x = e.getHitBoxDefaultX();
                e.getHitBox().y = e.getHitBoxDefaultY();
            }
            counter++;
        }
        return index;
    }

    public void checkPlayer(Entity entity){
        Player player = gamePanel.getPlayer();
        //Get entity's hitbox position
        entity.getHitBox().x = entity.getWorldX() + entity.getHitBox().x;
        entity.getHitBox().y = entity.getWorldY() + entity.getHitBox().y;
        
        //Get object's hitbox position
        player.getHitBox().x = player.getWorldX() + player.getHitBox().x;
        player.getHitBox().y = player.getWorldY() + player.getHitBox().y;

        //Check collision
        switch(entity.getDirection()){
            case "up":
                entity.getHitBox().y -= entity.getSpeed();
                if(entity.getHitBox().intersects(player.getHitBox()))
                    entity.setCollision(true);
                break;
            case "down":
                entity.getHitBox().y += entity.getSpeed();
                if(entity.getHitBox().intersects(player.getHitBox()))
                    entity.setCollision(true);      
                break;
            case "left":
                entity.getHitBox().x -= entity.getSpeed();
                if(entity.getHitBox().intersects(player.getHitBox()))
                    entity.setCollision(true);
                break;
            case "right":
                entity.getHitBox().x += entity.getSpeed();
                if(entity.getHitBox().intersects(player.getHitBox()))
                    entity.setCollision(true);
                break;
        }
        //Reset entity's hitbox position
        entity.getHitBox().x = entity.getHitBoxDefaultX();
        entity.getHitBox().y = entity.getHitBoxDefaultY();

        //Reset object's hitbox position
        player.getHitBox().x = player.getHitBoxDefaultX();
        player.getHitBox().y = player.getHitBoxDefaultY();
    }
}
