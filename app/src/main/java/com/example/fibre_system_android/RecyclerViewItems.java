package com.example.fibre_system_android;

public class RecyclerViewItems {

    String name;
    String size;
    int image;

    public RecyclerViewItems(String name, String size, int image) {
        this.name = name;
        this.size = size;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
