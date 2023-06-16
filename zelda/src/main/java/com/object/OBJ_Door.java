package com.object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
    
    BufferedImage sheet;

    public OBJ_Door(){
        setName("door");
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            setImage(sheet.getSubimage(0, 0, 16, 16));
            setImage(uTool.scaledImage(getImage(), getImage().getWidth() * 3, getImage().getHeight() * 3) );        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
