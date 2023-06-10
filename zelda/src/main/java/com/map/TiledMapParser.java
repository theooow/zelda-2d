package com.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class TiledMapParser {
    private int height;
    private int width;
    private ArrayList<LayerMap> layers;

    public TiledMapParser(String filePath) {
        layers = new ArrayList<>();
        JsonFactory jsonFactory = new JsonFactory();
        try {
            File jsonFile = new File(filePath);
            JsonParser jsonParser = jsonFactory.createParser(jsonFile);
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.getCurrentName();
                if("height".equals(fieldName)){
                    jsonParser.nextToken();
                    height = jsonParser.getIntValue();
                }else if ("width".equals(fieldName)){
                    jsonParser.nextToken();
                    width = jsonParser.getIntValue();
                }else if ("layers".equals(fieldName)){

                    while(jsonParser.nextToken() != JsonToken.END_ARRAY){
                        LayerMap layer = new LayerMap();
                        String field = jsonParser.getCurrentName();
                        if("data".equals(field)){
                            jsonParser.nextToken();
                            layer.setData(jsonParser.readValueAs(int[][].class));
                        } else if("height".equals(field)){
                            jsonParser.nextToken();
                            layer.setHeight(jsonParser.getIntValue());
                        } else if("width".equals(field)){
                            jsonParser.nextToken();
                            layer.setWidth(jsonParser.getIntValue());
                        } else if("x".equals(field)){
                            jsonParser.nextToken();
                            layer.setX(jsonParser.getIntValue());
                        } else if("y".equals(field)){
                            jsonParser.nextToken();
                            layer.setY(jsonParser.getIntValue());
                        } else if("name".equals(field)){
                            jsonParser.nextToken();
                            layer.setName(jsonParser.getText());
                        } else if("type".equals(field)){
                            jsonParser.nextToken();
                            layer.setType(jsonParser.getText());
                        } else if("visible".equals(field)){
                            jsonParser.nextToken();
                            layer.setVisible(jsonParser.getBooleanValue());
                        } else if("opacity".equals(field)){
                            jsonParser.nextToken();
                            layer.setOpacity(jsonParser.getFloatValue());
                        }
                        layers.add(layer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // getters and setters

    public int getHeight() {return height;}
    public void setHeight(int _height) {height = _height;}

    public int getWidth() {return width;}
    public void setWidth(int _width) {width = _width;}

    public ArrayList<LayerMap> getLayers() {return layers;}
    public void setLayers(ArrayList<LayerMap> _layers) {layers = _layers;}
}
