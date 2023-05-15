package org.example.dao;

import org.example.entity.Buyer;
import org.example.entity.Product;
import org.example.entity.Purchase;
import org.example.service.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PurchaseDAOImpl implements PurchaseDAO {

    Session session = null;

    @Override
    public String buy(String buyerName, String productTitle) {
        Buyer buyer = HibernateSessionFactoryUtil.getBuyerService().getBuyer(buyerName);
        Product product = HibernateSessionFactoryUtil.getProductService().getProduct(productTitle);
        Purchase purchase = new Purchase(buyer, product);
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(purchase);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return buyer + " buy " + product;
    }
}
