package com.entity.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.zelda.GamePanel;

public class TileManager {
    
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];


    public TileManager(GamePanel _gamePanel){
        gamePanel = _gamePanel;
        tile = new Tile[1440];
        mapTileNum = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        loadMap("/res/maps/world-01.txt");
        loadTiles();
    }

    private void loadTiles(){
        BufferedImage tileSheet = getTileSheet();
        int counter = 0;
        int tileSize = gamePanel.getOriginalTileSize();
        int numRows = tileSheet.getHeight() / tileSize;
        int numCols = tileSheet.getWidth() / tileSize;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int x = j * tileSize;
                int y = i * tileSize;
                int width = Math.min(tileSize, tileSheet.getWidth() - x);
                int height = Math.min(tileSize, tileSheet.getHeight() - y);

                tile[counter] = new Tile(tileSheet.getSubimage(x, y, width, height), false);
                counter++;
            }
        }
        tile[40].setIsSolid(true);
        tile[41].setIsSolid(true);
        tile[42].setIsSolid(true);
        tile[43].setIsSolid(true);

        tile[22].setIsSolid(true);
        tile[23].setIsSolid(true);
        tile[24].setIsSolid(true);
        tile[62].setIsSolid(true);
        tile[63].setIsSolid(true);
        tile[64].setIsSolid(true);
        tile[102].setIsSolid(true);
        tile[103].setIsSolid(true);
        tile[104].setIsSolid(true);
        tile[142].setIsSolid(true);
        tile[143].setIsSolid(true);
        tile[144].setIsSolid(true);
        tile[182].setIsSolid(true);
        tile[183].setIsSolid(true);
        tile[184].setIsSolid(true);
        tile[222].setIsSolid(true);
        tile[222].setIsSolid(true);
        tile[222].setIsSolid(true);
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()){
                String line = br.readLine();
                
                while(col < gamePanel.getMaxWorldCol()){
                    String[] tokens = line.split(" ");
                    int num = Integer.parseInt(tokens[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.getMaxWorldCol()){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    protected BufferedImage getTileSheet(){
        BufferedImage tileSheet = null;
        try{
            tileSheet = ImageIO.read(new File("./zelda/src/main/java/res/tiles/Overworld.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        return tileSheet;
    }

    public void draw(Graphics2D g2){
        // Activer l'anticrénelage
        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int tileSize = gamePanel.getTileSize();

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * tileSize;
            int worldY = worldRow * tileSize;
            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

            if(worldX + tileSize > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX()
            && worldX - tileSize < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX()
            && worldY + tileSize > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY()
            && worldY - tileSize < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY())
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, tileSize, tileSize, null);
            
            worldCol++;

            if(worldCol == gamePanel.getMaxWorldCol()){
                worldCol = 0;
                worldRow++; 
            }
        }
    }

    public Tile[] getTile() {
        return tile;
    }

    public int getMapTileNum(int col, int row) {
        return mapTileNum[col][row];
    }
}
