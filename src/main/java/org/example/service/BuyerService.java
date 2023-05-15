package org.example.service;

import org.example.dao.BuyerDAO;
import org.example.dao.BuyerDAOImpl;
import org.example.entity.Buyer;
import org.example.entity.Product;

import java.util.List;

public class BuyerService {
    private final BuyerDAO buyerDAO = new BuyerDAOImpl();

    public void addBuyer(Buyer buyer){
        buyerDAO.add(buyer);
    }

    public Buyer getBuyer(String buyerName){
        return buyerDAO.get(buyerName);
    }

    public Buyer removeBuyer(String name){
        return buyerDAO.remove(name);
    }

    public List<Product> getAllProducts(String buyerName){
        return buyerDAO.getAllProducts(buyerName);
    }

}
