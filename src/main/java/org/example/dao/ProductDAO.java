package org.example.dao;

import org.example.entity.Buyer;
import org.example.entity.Product;

import java.util.List;

public interface ProductDAO {

    void add(Product product);
    Product get(String productTitle);
    Product remove(String productTitle);
    List<Buyer> getAllBuyers(String productTitle);
}
