package com.example.greedydevs.festivalx;

/**
 * Created by Paul on 11/18/2017.
 */

public class Group {
    private String[] members;
    private String[] products;
    private int membersCount, productsCount;
    public Group(String[] members, String products[])
    {
        members = new String[200];
        products = new String[200];
        this.members = members;
        this.products = products;
        this.membersCount = members.length;
        this.productsCount = products.length;
    }

    public int getMembersCount()
    {
        return this.membersCount;
    }

    public int getProductsCount()
    {
        return this.productsCount;
    }
    public String[] getMembers()
    {
        return this.members;
    }

    public String[] getProduts()
    {
        return this.products;
    }

    public String getProductAt(int index)
    {
        return this.products[index];
    }
    public String getMemberAt(int index)
    {
        return this.members[index];
    }
}
