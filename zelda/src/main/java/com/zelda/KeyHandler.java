package com.zelda;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, spacePressed, enterPressed;

    public KeyHandler(GamePanel _gp){
        gp = _gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.gameState == gp.TITLE_STATE){
            if(code == KeyEvent.VK_Z){
                if(gp.getMyUI().commandNum == 0)
                    gp.getMyUI().commandNum = 2;
                else
                    gp.getMyUI().commandNum--;
            }
            if(code == KeyEvent.VK_S){
                if(gp.getMyUI().commandNum == 2)
                    gp.getMyUI().commandNum = 0;
                else
                    gp.getMyUI().commandNum++;
            }
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                if(gp.getMyUI().commandNum == 0){
                    gp.gameState = gp.PLAY_STATE;
                    gp.playMusic(0);
                }
                else if(gp.getMyUI().commandNum == 1){

                }else if(gp.getMyUI().commandNum == 2)
                    System.exit(0);
            }

        }else if(gp.gameState == gp.PLAY_STATE){
            if(code == KeyEvent.VK_Z){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_Q){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_SHIFT)
                shiftPressed = true;
            if(code == KeyEvent.VK_ESCAPE)
                gp.gameState = gp.PAUSE_STATE;
            if(code == KeyEvent.VK_SPACE)
                spacePressed = true;
            if(code == KeyEvent.VK_ENTER)
                enterPressed = true;
        }
        else if(gp.gameState == gp.PAUSE_STATE){
            if(code == KeyEvent.VK_ESCAPE)
                gp.gameState = gp.PLAY_STATE;
        }
        else if(gp.gameState == gp.DIALOG_STATE){
            if(code == KeyEvent.VK_SPACE ||code == KeyEvent.VK_ENTER)
                gp.gameState = gp.PLAY_STATE;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_Z){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_Q){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SHIFT)
            shiftPressed = false;
    }
    
}
