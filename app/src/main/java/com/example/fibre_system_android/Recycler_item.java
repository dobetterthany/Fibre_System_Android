package com.example.fibre_system_android;

import java.io.Serializable;
import java.util.HashMap;

public class Recycler_item implements Serializable {
    String name;
    int length; //side to side measurement
    int height; //top to bottom measurement
    int image;

    float posX,posY;

    boolean isGeneric;
    HashMap<String, Boolean> colours;

    public ShowerRange getShowerRange() {
        return showerRange;
    }

    ShowerRange showerRange;

    public Recycler_item(String name, int length, int width, int image ,ShowerRange showerRange) {
        this.name = name;
        this.length = length;
        this.height = width;
        this.image = image;
        this.showerRange = showerRange;

        initColours();
        isGeneric = true;
    }

    public Recycler_item(String name, int length, int width, int image, Boolean chrome, boolean black, boolean white,ShowerRange showerRange) {
        this.name = name;
        this.length = length;
        this.height = width;
        this.image = image;
        this.showerRange = showerRange;

        initColours();
        isGeneric = false;

        colours.put("chrome", chrome);
        colours.put("black", black);
        colours.put("white", white);
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

    private void initColours()
    {
        colours = new HashMap<String, Boolean>();
        colours.put("chrome", false);
        colours.put("black", false);
        colours.put("white", false);
        colours.put("gunmetal", false);
    }

    public void setColours(Boolean chrome, boolean black, boolean white, boolean gunmetal)
    {
        colours.put("chrome", chrome);
        colours.put("black", black);
        colours.put("white", white);
        colours.put("gunmetal", gunmetal);
    }

    public void SetPos(float x, float y)
    {
        this.posX = x;
        this.posY = y;
    }

    public float GetX()
    {
        return this.posX;
    }

    public float GetY()
    {
        return this.posY;
    }
}
