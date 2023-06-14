package com.zelda;

import com.object.OBJ_Key;

public class AssetSetter {
    
    GamePanel gamePanel;

    public AssetSetter(GamePanel _gamePanel){
        gamePanel = _gamePanel;
    }

    public void setObject(){
        gamePanel.getObj()[0] = new OBJ_Key();
        gamePanel.getObj()[0].setWorldX(26*gamePanel.getTileSize());
        gamePanel.getObj()[0].setWorldY(12*gamePanel.getTileSize());
    }
}
