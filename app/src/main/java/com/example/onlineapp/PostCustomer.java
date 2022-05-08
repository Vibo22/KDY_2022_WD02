package com.example.onlineapp;

public class PostCustomer {
    String menuName;
    String menuPrice;
    String image;

    public PostCustomer() {
    }

    public PostCustomer(String menuName, String menuPrice, String image) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.image = image;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
