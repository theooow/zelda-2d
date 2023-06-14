package com.object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {
    
    BufferedImage sheet;

    public OBJ_Chest(){
        setName("chest");
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
            setImage(sheet.getSubimage(0, 0, 16, 16));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
