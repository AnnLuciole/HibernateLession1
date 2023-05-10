package org.example.dao;


import org.example.entity.Buyer;
import org.example.entity.Product;

import java.util.List;

public interface BuyerDAO {
    void add(Buyer buyer);
    Buyer get(String buyerName);
    Buyer remove(String name);
    List<Product> getAllProducts(String buyerName);
}
