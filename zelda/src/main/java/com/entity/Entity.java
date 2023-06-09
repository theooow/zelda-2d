package com.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity {
    
    protected int x, y;
    protected int speed;

    protected BufferedImage spriteUp1, spriteUp2, spriteUp3, spriteRight1, spriteRight2, spriteRight3, spriteDown1, spriteDown2, spriteDown3, spriteLeft1, spriteLeft2, spriteLeft3;
    protected String direction;

    protected int spriteCounter = 0;
    protected int spriteNumber = 1;

    protected BufferedImage getSpriteSheet(){
        BufferedImage spriteSheet = null;
        try{
            spriteSheet = ImageIO.read(new File("./zelda/src/main/java/com/entity/player/character.png"));
        }catch(IOException e){
            //Afficher le chemin courant
            System.err.println("Current path: " + System.getProperty("user.dir"));
            e.printStackTrace();
        }
        return spriteSheet;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getSpeed() {return speed;}
    public BufferedImage getSpriteUp1() {return spriteUp1;}
    public BufferedImage getSpriteUp2() {return spriteUp2;}
    public BufferedImage getSpriteUp3() {return spriteUp3;}
    public BufferedImage getSpriteDown1() {return spriteDown1;}
    public BufferedImage getSpriteDown2() {return spriteDown2;}
    public BufferedImage getSpriteDown3() {return spriteDown3;}
    public BufferedImage getSpriteLeft1() {return spriteLeft1;}
    public BufferedImage getSpriteLeft2() {return spriteLeft2;}
    public BufferedImage getSpriteLeft3() {return spriteLeft3;}
    public BufferedImage getSpriteRight1() {return spriteRight1;}
    public BufferedImage getSpriteRight2() {return spriteRight2;}
    public BufferedImage getSpriteRight3() {return spriteRight3;}
    public String getDirection() {return direction;}
}
