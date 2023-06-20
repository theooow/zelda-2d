package com.entity.npc;

import java.util.Random;

import com.entity.Entity;
import com.zelda.GamePanel;

public class NPC_1 extends Entity {
    
    public NPC_1(GamePanel _gamePanel){
        super(_gamePanel);
        direction = "down";
        speed = 1;
        
        loadSprite("./zelda/src/main/java/res/npcs/npc1.png");
    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // from 1 to 100
            if(i <= 25)
                direction = "up";
            else if(i > 25 && i <= 50)
                direction = "down";
            else if(i > 50 && i <= 75)
                direction = "left";
            else 
                direction = "right";
            actionLockCounter = 0;
        }
    }

}
