package com.map;

public class LayerMap {
    private int[] data;
    private int height;
    private int width;
    private int x;
    private int y;
    private String name;
    private String type;
    private boolean visible;
    private float opacity;

    public LayerMap() {
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

    // getters and setters
    public int[] getData() {return data;}
    public void setData(int[] data) {this.data = data;}

    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}

    public int getWidth() {return width;}
    public void setWidth(int width) {this.width = width;}

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}

    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public boolean isVisible() {return visible;}
    public void setVisible(boolean visible) {this.visible = visible;}

    public float getOpacity() {return opacity;}
    public void setOpacity(float opacity) {this.opacity = opacity;}

    public int[][] getMap(){
        return convertToTwoDimensional(data, height, width);
    }
}
