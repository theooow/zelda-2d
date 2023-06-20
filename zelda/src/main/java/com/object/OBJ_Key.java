package com.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.zelda.GamePanel;
public class OBJ_Key extends SuperObject {
    
    public OBJ_Key(GamePanel _gp){
        super(_gp);
        setName("key");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
