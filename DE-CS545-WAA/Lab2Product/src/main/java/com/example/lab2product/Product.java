package com.example.lab2product;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {

    @Size(min=2, max = 5, message = "Product number size must be between 2 and 5")
    @NotNull(message = "Product number cannot be null")
    private String number;

    @NotNull(message = "Product name cannot be null")
    @Size(min=2, max=20, message = "Product name size must be between 2 and 20")
    private String name;
    @Min(0)
    private double price;
    private int qty;
    public Product() {}

    public Product(String number, String name, double price) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.qty = 0;
    }

    public void increaseQty() {
        this.qty++;
    }

    public void decreaseQty() {
        this.qty--;
    }

    public void setQty(int val) {
        this.qty = val;
    }

    public int getQty() {
        return this.qty;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String productNumber) {
        this.number = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "productNumber" + this.number + " productName : " + this.name + " Price : " + this.price + " Qty  :" + this.qty;
    }
}
