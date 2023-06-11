package com.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TiledMapParser {
    private int height;
    private int width;
    private ArrayList<LayerMap> layers;

    public TiledMapParser(String filePath) {
        layers = new ArrayList<>();
        try {
            File jsonFile = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonParser jsonParser = objectMapper.getFactory().createParser(jsonFile);
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.getCurrentName();
                if("height".equals(fieldName)){
                    jsonParser.nextToken();
                    height = jsonParser.getIntValue();
                }else if ("width".equals(fieldName)){
                    jsonParser.nextToken();
                    width = jsonParser.getIntValue();
                }else if ("layers".equals(fieldName)) {
                    jsonParser.nextToken();

                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                        String field = jsonParser.getCurrentName();
                        LayerMap layer = new LayerMap();

                        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                            field = jsonParser.getCurrentName();
                            jsonParser.nextToken();
                            
                            if ("data".equals(field)) {
                                int [] temp = new int[height*width];
                                int counter = 0;
                                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                                    temp[counter++] = jsonParser.getIntValue();
                                }
                                layer.setData(temp);
                            } else if ("height".equals(field)) {
                                layer.setHeight(jsonParser.getIntValue());
                            } else if ("width".equals(field)) {
                                layer.setWidth(jsonParser.getIntValue());
                            } else if ("x".equals(field)) {
                                layer.setX(jsonParser.getIntValue());
                            } else if ("y".equals(field)) {
                                layer.setY(jsonParser.getIntValue());
                            } else if ("name".equals(field)) {
                                layer.setName(jsonParser.getText());
                            } else if ("type".equals(field)) {
                                layer.setType(jsonParser.getText());
                            } else if ("visible".equals(field)) {
                                layer.setVisible(jsonParser.getBooleanValue());
                            } else if ("opacity".equals(field)) {
                                layer.setOpacity(jsonParser.getFloatValue());
                            }
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
