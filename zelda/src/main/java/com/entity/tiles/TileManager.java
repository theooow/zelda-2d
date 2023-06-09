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
        mapTileNum = new int[gamePanel.getMaxScreencol()][gamePanel.getMaxScreenrow()];
        loadMap("/com/entity/maps/map-01.txt");
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
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gamePanel.getMaxScreencol() && row < gamePanel.getMaxScreenrow()){
                String line = br.readLine();
                
                while(col < gamePanel.getMaxScreencol()){
                    String[] tokens = line.split(" ");
                    int num = Integer.parseInt(tokens[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.getMaxScreencol()){
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
            tileSheet = ImageIO.read(new File("./zelda/src/main/java/com/entity/tiles/Overworld.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        return tileSheet;
    }

    public void draw(Graphics2D g2){
        int tileSize = gamePanel.getTileSize();
        for(int i = 0; i < gamePanel.getHeight() / tileSize; i++){
            for(int j = 0; j < gamePanel.getWidth() / tileSize; j++) {
                int tileNum = mapTileNum[j][i];
                if(tileNum >= 0){
                    g2.drawImage(tile[tileNum].getImage(), j * tileSize, i * tileSize, tileSize, tileSize, null);
                }
            }
        }
    }
}
