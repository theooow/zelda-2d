package com.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.map.LayerMap;
import com.zelda.GamePanel;
import com.zelda.UtilityTool;

public class TileManager {
    
    GamePanel gamePanel;
    ArrayList<Tile> tile;

    public TileManager(GamePanel _gamePanel){
        gamePanel = _gamePanel;
        tile = new ArrayList<>();

        // between [0 - 175] // 176
        loadTiles("./zelda/src/main/java/res/tiles/Overworld.png", false);

        // between [176 - 1039] // 864
        loadTiles("./zelda/src/main/java/res/tiles/Objects.png", true);

        for(LayerMap layer :_gamePanel.getParser().getLayers()){
            if(layer.getName().contains("collision"))
                for(int i = 0; i < layer.getData().length; i++)
                    if(layer.getData()[i] != 0)
                        tile.get(layer.getData()[i]).setIsSolid(true);
        }
    }

    //chope les tiles du fichier //!NE SERT PAS A LES AFFICHER
    private void loadTiles(String filePath, boolean isSolid){
        BufferedImage tileSheet = getSheet(filePath);

        int tileSize = gamePanel.getTileSize();
        int numRows = tileSheet.getHeight() / tileSize;
        int numCols = tileSheet.getWidth() / tileSize; 

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int x = j * tileSize;
                int y = i * tileSize;
                int width = Math.min(tileSize, tileSheet.getWidth() - x);
                int height = Math.min(tileSize, tileSheet.getHeight() - y);
                
                tile.add(new Tile(tileSheet.getSubimage(x, y, width, height), isSolid));
            }
        }
    }

    protected BufferedImage getSheet(String filePath){
        BufferedImage tileSheet = null;
        try{
            tileSheet = ImageIO.read(new File(filePath));
            int scaledWidth = tileSheet.getWidth() * gamePanel.getScale();
            int scaledHeight = tileSheet.getHeight() * gamePanel.getScale();

            UtilityTool uTool = new UtilityTool();
            BufferedImage scaledImage = uTool.scaledImage(tileSheet, scaledWidth, scaledHeight);
            tileSheet = scaledImage;
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return tileSheet;
    }

    public void draw(Graphics2D g2, LayerMap layer){

        int worldCol = 0;
        int worldRow = 0;
        int tileSize = gamePanel.getTileSize();
        int[][] map;
        try {
            map = layer.getMap();

            while(worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()){
                int tileNum = map[worldCol][worldRow];
                int worldX = worldCol * tileSize;
                int worldY = worldRow * tileSize;
                int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
                int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();
                //System.out.println("screenX: " + screenX + " screenY: " + screenY);
                

                if(worldX + tileSize > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX()
                && worldX - tileSize < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX()
                && worldY + tileSize > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY()
                && worldY - tileSize < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()){
                    if(tileNum > 0)
                        g2.drawImage(tile.get(tileNum-1).getImage(), screenX, screenY, null);
                }
                worldCol++;

                if(worldCol == gamePanel.getMaxWorldCol()){
                    worldCol = 0;
                    worldRow++; 
                }

            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tile> getTile() {
        return tile;
    }

    public void update() 
    {
        int _screenX = gamePanel.getScreenWidth()/2 - gamePanel.getTileSize()/2;
        int _screenY = gamePanel.getScreenHeight()/2 - gamePanel.getTileSize()/2;

        // update screenX and screenY
        gamePanel.getPlayer().setScreenX(_screenX);
        gamePanel.getPlayer().setScreenY(_screenY);

    }
}
