package com.example.onlineapp.database;

public class Packages {

    private String Name;
    private String Description;
    private Double Price;
    private String ImageUrl;

    public Packages(){

    }

    public Packages(String name,String des,Double price, String imageUrl) {

        if (name.trim().equals("")){
            name="No Name";
        }
        Name=name;
        Description=des;
        Price=price;
        ImageUrl=imageUrl;
    }


    public String getDescription() {
        return Description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

}
