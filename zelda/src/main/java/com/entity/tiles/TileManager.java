package com.entity.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.map.LayerMap;
import com.map.TiledMapParser;
import com.zelda.GamePanel;

public class TileManager {
    
    GamePanel gamePanel;
    ArrayList<Tile> tile;
    int mapTileNum[][];
    TiledMapParser parser;


    public TileManager(GamePanel _gamePanel){
        gamePanel = _gamePanel;
        tile = new ArrayList<>();
        mapTileNum = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        //loadMap("/res/maps/world-01.txt");

        // between [0 - 175] // 176
        loadTiles("./zelda/src/main/java/res/tiles/Overworld.png", false);

        // between [176 - 1039] // 864
        loadTiles("./zelda/src/main/java/res/tiles/Objects.png", true);

        parser = new TiledMapParser("./zelda/src/main/java/res/maps/world01.json");
    }

    private void loadTiles(String filePath, boolean isSolid){
        BufferedImage tileSheet = getSheet(filePath);

        int tileSize = gamePanel.getOriginalTileSize();
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

        // Here declare the tiles that are solid
        
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

    protected BufferedImage getSheet(String filePath){
        BufferedImage tileSheet = null;
        try{
            tileSheet = ImageIO.read(new File(filePath));
        }catch(IOException e){
            e.printStackTrace();
        }
        return tileSheet;
    }

    public static int[][] convertToTwoDimensional(int[] array, int rows, int columns) {
        if (array.length != rows * columns) {
            throw new IllegalArgumentException("Le nombre d'éléments du tableau ne correspond pas à la taille bidimensionnelle spécifiée.");
        }

        int[][] twoDimensionalArray = new int[rows][columns];
        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                twoDimensionalArray[j][i] = array[index++];
            }
        }

        return twoDimensionalArray;
    }


    public void draw(Graphics2D g2, LayerMap layer){

        int worldCol = 0;
        int worldRow = 0;
        int tileSize = gamePanel.getTileSize();
        int[][] map;
        try {
            map = convertToTwoDimensional(layer.getData(), layer.getHeight(), layer.getWidth());

            while(worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()){
                int tileNum = map[worldCol][worldRow];
                int worldX = worldCol * tileSize;
                int worldY = worldRow * tileSize;
                int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
                int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

                if(worldX + tileSize > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX()
                && worldX - tileSize < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX()
                && worldY + tileSize > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY()
                && worldY - tileSize < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()){
                    if(tileNum > 0)
                        g2.drawImage(tile.get(tileNum-1).getImage(), screenX, screenY, tileSize, tileSize, null);
                    else 
                        g2.drawImage(tile.get(56).getImage(), screenX, screenY, tileSize, tileSize, null);
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

    public int getMapTileNum(int col, int row) {
        return mapTileNum[col][row];
    }
}
