package com.object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zelda.GamePanel;

public class OBJ_Chest extends SuperObject {
    
    BufferedImage sheet;
    boolean state = false;
    GamePanel gp;

    public OBJ_Chest(GamePanel _gp){
        gp = _gp;
        setName("chest");
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
            setImage(sheet.getSubimage(0, 0, 16, 16));
            setImage(uTool.scaledImage(getImage(), gp.getTileSize(), gp.getTileSize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIsSolid(true);
    }

    public void open(){
        setImage(sheet.getSubimage(16, 0, 16, 16));
        setImage(uTool.scaledImage(getImage(), gp.getTileSize(), gp.getTileSize()));
        state = true;
    }

    public boolean getState(){
        return state;
    }

}
