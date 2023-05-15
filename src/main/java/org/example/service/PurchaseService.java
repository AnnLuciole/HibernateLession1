package org.example.service;

import org.example.dao.PurchaseDAO;
import org.example.dao.PurchaseDAOImpl;

public class PurchaseService {

    private final PurchaseDAO purchaseDAO = new PurchaseDAOImpl();

    public String buyProduct(String buyerName, String productTitle) {
        return purchaseDAO.buy(buyerName, productTitle);
    }
}
