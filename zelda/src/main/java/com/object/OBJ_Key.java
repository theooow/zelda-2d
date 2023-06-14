package com.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
    
    public OBJ_Key(){
        setName("key");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
