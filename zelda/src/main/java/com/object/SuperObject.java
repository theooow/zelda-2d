package com.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.zelda.GamePanel;
import com.zelda.UtilityTool;

public class SuperObject {
    
    private GamePanel gp;
    private BufferedImage image;
    private String name;
    private boolean isSolid = false;
    private int worldX, worldY;
    private Rectangle hitBox = new Rectangle(0,0,48,48);
    private int hitBoxDefaultX = hitBox.x;
    private int hitBoxDefaultY = hitBox.y;
    UtilityTool uTool = new UtilityTool();

    public SuperObject(GamePanel _gp){
        gp = _gp;
    }

    public void draw(Graphics2D g2){
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if(worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX()
        && worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX()
        && worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY()
        && worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY())
            g2.drawImage(image, screenX, screenY, null);
    }

    // geters

    public BufferedImage getImage(){return image;}
    public String getName(){return name;}
    public boolean getIsSolid(){return isSolid;}
    public int getWorldX(){return worldX;}
    public int getWorldY(){return worldY;}
    public Rectangle getHitBox(){return hitBox;}
    public int getHitBoxDefaultX(){return hitBoxDefaultX;}
    public int getHitBoxDefaultY(){return hitBoxDefaultY;}

    // seters

    public void setImage(BufferedImage _image){
        image = uTool.scaledImage(_image, gp.getTileSize(), gp.getTileSize());
    }
    public void setName(String _name){name = _name;}
    public void setIsSolid(boolean _isSolid){isSolid = _isSolid;}
    public void setWorldX(int _worldX){worldX = _worldX;}
    public void setWorldY(int _worldY){worldY = _worldY;}
    public void setHitBox(Rectangle _hitBox){hitBox = _hitBox;}
    public void setHitBoxDefaultX(int _hitBoxDefaultX){hitBoxDefaultX = _hitBoxDefaultX;}
    public void setHitBoxDefaultY(int _hitBoxDefaultY){hitBoxDefaultY = _hitBoxDefaultY;}
}
