package org.example.service;

import org.example.entity.Buyer;
import org.example.entity.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private static BuyerService buyerService;

    private static ProductService productService;

    private static PurchaseService purchaseService;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                return sessionFactory = new Configuration().
                        configure("hibernate.cfg.xml").
                        addAnnotatedClass(Buyer.class).
                        addAnnotatedClass(Product.class).
                        buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    public static BuyerService getBuyerService(){
        if (buyerService == null){
            buyerService = new BuyerService();
        }
        return buyerService;
    }

    public static ProductService getProductService(){
        if(productService == null){
            productService = new ProductService();
        }
        return productService;
    }

    public static PurchaseService getPurchaseService(){
        if (purchaseService == null){
            purchaseService = new PurchaseService();
        }
        return purchaseService;
    }

    public static void addPerson(String name){
        Buyer buyer = HibernateSessionFactoryUtil.getBuyerService().getBuyer(name);
        if (buyer == null) {
            buyer = new Buyer(name);
            HibernateSessionFactoryUtil.getBuyerService().addBuyer(buyer);
        }
        System.out.println(buyer + " добавлен.");
    }

    public static void showProductsByBuyer(String buyerName) {
        List<Product> allProducts = HibernateSessionFactoryUtil.
                getBuyerService().
                getAllProducts(buyerName);
        for (Product p:allProducts) {
            System.out.println(p);
        }
    }

    public static void findBuyersByProductTitle(String productTitle) {
        List<Buyer> allBuyers = HibernateSessionFactoryUtil.
                getProductService().
                getAllBuyers(productTitle);
        for (Buyer b:allBuyers) {
            System.out.println(b);
        }
    }

    public static void removeBuyer(String name) {
        Buyer buyer = HibernateSessionFactoryUtil.getBuyerService().removeBuyer(name);
        System.out.println(buyer + " удален.");
    }

    public static void removeProduct(String title) {
        Product product = HibernateSessionFactoryUtil.getProductService().removeProduct(title);
        System.out.println(product + " удален.");
    }

    public static void buy(String buyerName, String productTitle) {
        System.out.println(HibernateSessionFactoryUtil.getPurchaseService().buyProduct(buyerName, productTitle));
    }
}
