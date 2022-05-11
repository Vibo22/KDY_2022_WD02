package com.example.onlineapp.database;

import com.google.android.gms.common.data.SingleRefDataBufferIterator;

public class PostModel {

    String menuId;
    String menuName;
    String menuPrice;
    String image;

    public PostModel() {
    }

    public PostModel(String menuId, String menuName, String menuPrice, String image) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.image = image;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
