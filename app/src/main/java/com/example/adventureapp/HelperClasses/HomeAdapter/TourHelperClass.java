package com.example.adventureapp.HelperClasses.HomeAdapter;

public class TourHelperClass {

    int image;
    String title, description, price;

    public TourHelperClass(int image, String price, String title, String description) {
        this.image = image;
        this.title = title;
        this.price = price;
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
