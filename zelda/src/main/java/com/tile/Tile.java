package com.tile;

import java.awt.image.BufferedImage;

public class Tile {
        
    private BufferedImage image;
    private boolean isSolid;

    public Tile(BufferedImage _image, boolean _isSolid){
        image = _image;
        isSolid = _isSolid;
    }

    public BufferedImage getImage(){return image;}
    public boolean getIsSolid(){return isSolid;}

    public void setImage(BufferedImage _image){image = _image;}
    public void setIsSolid(boolean _isSolid){isSolid = _isSolid;}
}
