package com.example.fibre_system_android;

import java.util.ArrayList;
import java.util.Map;

public class RecyclerViewItems {

    String name;
    int length; //side to side measurement
    int height; //top to bottom measurement
    int image;

    Map<String, Boolean> colours;
    public RecyclerViewItems(String name, int length, int width, int image) {
        this.name = name;
        this.length = length;
        this.height = width;
        this.image = image;

        colours.put("black", false);
        colours.put("chrome", false);
        colours.put("white", false);
        colours.put("gunmetal", false);

    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSizeString()
    {
        String sizeString = length + " X " + height;
        return sizeString;
    }
}
