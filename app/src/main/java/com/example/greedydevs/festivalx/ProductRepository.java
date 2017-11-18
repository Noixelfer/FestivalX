package com.example.greedydevs.festivalx;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Paul on 11/18/2017.
 */

public class ProductRepository {
    final FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
    DatabaseReference ProductsRef;
    private Product[] products;
    private int length =0;
    public ProductRepository()
    {

        products = new Product[100];
        ProductsRef = dataBase.getReference("products");
        ProductsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=0; i < dataSnapshot.getChildrenCount(); i++)
                {
                    length+=1;
                    String productName = "product" + String.valueOf(i + 1);
                    String name = dataSnapshot.child(productName).child("name").getValue().toString();
                    int price = Integer.valueOf(dataSnapshot.child(productName).child("price").getValue().toString());
                    products[i] = new Product(name, price);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }
    public int getLength()
    {
        return this.length;
    }

    public Product[] getProducts()
    {
        return this.products;
    }

    public Product getProductAt(int index) {
        return products[index];
    }
}
