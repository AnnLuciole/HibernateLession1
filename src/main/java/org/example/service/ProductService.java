package org.example.service;

import org.example.dao.ProductDAO;
import org.example.dao.ProductDAOImpl;
import org.example.entity.Buyer;
import org.example.entity.Product;

import java.util.List;

public class ProductService {

    private ProductDAO productDAO = new ProductDAOImpl();

    public void addProduct(Product product){
        productDAO.add(product);
    }

    public Product getProduct(String productTitle){
        return productDAO.get(productTitle);
    }

    public Product removeProduct(String title){
        return productDAO.remove(title);
    }

    public List<Buyer> getAllBuyers(String productTitle){
        return productDAO.getAllBuyers(productTitle);
    }
}
