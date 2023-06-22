package com.zelda;

import java.awt.Rectangle;

import com.entity.player.Player;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gamePanel){
        gp = gamePanel;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent(){
        if(hit(25,8,"up")) damagePit(gp.DIALOG_STATE);
        if(hit(24,6,"up")) healingPool(gp.DIALOG_STATE);
    }

    private void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.getMyUI().currentDialog = "You tooked some damages";
        gp.player.setLife(-1);
    }

    private void healingPool(int gameState){
        if(gp.getKeyHandler().spacePressed){
            gp.gameState = gameState;
            gp.getMyUI().currentDialog = "You healed !";
            gp.player.setLife(gp.player.getMaxLife() - gp.player.getLife());
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDir){
        boolean hit = false;
        Player p = gp.getPlayer();
        p.getHitBox().x = p.getWorldX() + p.getHitBox().x;
        p.getHitBox().y = p.getWorldY() + p.getHitBox().y;
        eventRect.x = eventCol * gp.getTileSize() + eventRect.x;
        eventRect.y = eventRow * gp.getTileSize() + eventRect.y;

        if(p.getHitBox().intersects(eventRect))
            if(p.getDirection().contentEquals(reqDir) || reqDir.contentEquals("any"))
                hit = true;

        p.getHitBox().x = p.getHitBoxDefaultX();
        p.getHitBox().y = p.getHitBoxDefaultY();
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }
}
