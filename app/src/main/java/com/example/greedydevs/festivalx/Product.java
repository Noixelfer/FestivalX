package com.example.greedydevs.festivalx;


public class Product {
    private String name; int price;
    public Product(String name, int price)
    {
        this.name = name;
        this.price = price;
    }
    public String getName()
    {
        return this.name;
    }
    public int getPrice()
    {
        return this.price;
    }
}
