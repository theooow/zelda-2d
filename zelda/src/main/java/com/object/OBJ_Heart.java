package com.object;

import java.io.IOException;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.zelda.GamePanel;

public class OBJ_Heart extends SuperObject{

    BufferedImage sheet;
    
    public OBJ_Heart(GamePanel _gp){
        super(_gp);
        setName("heart");
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/res/objects/hearts.png"));
            setImage(sheet.getSubimage(32, 0, 16, 16));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHalf(){
        setImage(sheet.getSubimage(16, 0, 16, 16));
    }

    public void setEmpty(){
        setImage(sheet.getSubimage(0, 0, 16, 16));
    }
}
