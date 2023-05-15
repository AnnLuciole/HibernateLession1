package org.example.dao;

import org.example.entity.Buyer;
import org.example.entity.Product;
import org.example.entity.Purchase;
import org.example.service.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    Session session = null;

    @Override
    public void add(Product product) {
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public Product get(String productTitle) {
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Product product = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            product = session.createQuery("from Product p where title = :title", Product.class).
                    setParameter("title", productTitle).
                    getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return product;
    }

    @Override
    public Product remove(String productTitle) {
        Product product = get(productTitle);
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return product;
    }

    @Override
    public List<Buyer> getAllBuyers(String productTitle) {
        Product product = get(productTitle);
        if(product == null){
            return null;
        }
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        List<Buyer> allBuyers = null;
        try {
            transaction = session.beginTransaction();
            allBuyers = product.getAllPurchases().stream().map(Purchase::getBuyer).toList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return allBuyers;
    }
}
