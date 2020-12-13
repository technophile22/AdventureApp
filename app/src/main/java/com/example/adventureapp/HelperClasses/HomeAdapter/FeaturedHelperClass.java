package com.example.adventureapp.HelperClasses.HomeAdapter;

public class FeaturedHelperClass {

    int image;
    String title, description, price;

    public FeaturedHelperClass(int image, String price, String title, String description) {
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() { return price; }
}
