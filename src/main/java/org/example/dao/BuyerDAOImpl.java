package org.example.dao;

import org.example.entity.Buyer;
import org.example.entity.Product;
import org.example.service.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BuyerDAOImpl implements BuyerDAO{

    Session session = null;

    @Override
    public void add(Buyer buyer){
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(buyer);
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
    public Buyer get(String buyerName) {
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Buyer buyer = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            buyer = session.createQuery("from Buyer b where name = :name", Buyer.class).
                    setParameter("name", buyerName).
                    getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
        if (buyer == null){
            return null;
        }
        return buyer;
    }

    @Override
    public Buyer remove(String name) {
        Buyer buyer = get(name);
        if (buyer == null) {
            return null;
        }
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(buyer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return buyer;
    }

    @Override
    public List<Product> getAllProducts(String buyerName) {
        Buyer buyer = get(buyerName);
        if (buyer == null) {
            return null;
        }
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        List<Product> allProducts = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            allProducts = buyer.getAllProducts();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
        if(allProducts.isEmpty()){
            return null;
        }
        return allProducts;
    }
}
