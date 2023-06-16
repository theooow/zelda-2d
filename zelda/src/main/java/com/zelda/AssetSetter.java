package com.zelda;

import com.object.OBJ_Chest;
import com.object.OBJ_Key;

public class AssetSetter {
    
    GamePanel gamePanel;

    public AssetSetter(GamePanel _gamePanel){
        gamePanel = _gamePanel;
    }

    public void setObject(){
        gamePanel.getObj()[0] = new OBJ_Key(gamePanel);
        gamePanel.getObj()[0].setWorldX(25*gamePanel.getTileSize());
        gamePanel.getObj()[0].setWorldY(12*gamePanel.getTileSize());

        gamePanel.getObj()[1] = new OBJ_Chest(gamePanel);
        gamePanel.getObj()[1].setWorldX(24*gamePanel.getTileSize());
        gamePanel.getObj()[1].setWorldY(10*gamePanel.getTileSize());
    }
}
