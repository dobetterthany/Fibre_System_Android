package com.example.fibre_system_android;

public class RecyclerViewItems {

    String name;
    int length; //side to side measurement
    int height; //top to bottom measurement
    int image;

    public RecyclerViewItems(String name, int length, int height, int image) {
        this.name = name;
        this.length = length;
        this.height = height;
        this.image = image;
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
