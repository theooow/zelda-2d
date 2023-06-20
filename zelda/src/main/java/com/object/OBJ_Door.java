package com.object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zelda.GamePanel;

public class OBJ_Door extends SuperObject {
    
    BufferedImage sheet;

    public OBJ_Door(GamePanel _gp){
        super(_gp);
        setName("door");
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            setImage(sheet.getSubimage(0, 0, 16, 16));      } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
