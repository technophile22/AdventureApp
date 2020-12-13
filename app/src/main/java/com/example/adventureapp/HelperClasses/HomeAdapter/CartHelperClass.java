package com.example.adventureapp.HelperClasses.HomeAdapter;

public class CartHelperClass {

    int image;
    String title, quantity, price;

    public CartHelperClass(int image, String price, String title, String quantity) {
        this.image = image;
        this.price = price;
        this.title = title;
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }


    public String getTitle() {
        return title;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() { return price; }
}
