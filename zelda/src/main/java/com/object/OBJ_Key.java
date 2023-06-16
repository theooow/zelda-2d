package com.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.zelda.GamePanel;

public class OBJ_Key extends SuperObject {
    
    public OBJ_Key(GamePanel _gp){
        setName("key");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png")));
            setImage(uTool.scaledImage(getImage(), _gp.getTileSize(), _gp.getTileSize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
