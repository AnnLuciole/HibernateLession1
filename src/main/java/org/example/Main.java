package org.example;

import org.example.service.HibernateSessionFactoryUtil;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        HibernateSessionFactoryUtil.getSessionFactory();
        System.out.println("Введите команду:");
        Scanner sc = new Scanner(System.in);
        String[] query = sc.nextLine().trim().split(" ");
        switch (query[0]) {
            case "/showProductsByPerson":
                HibernateSessionFactoryUtil.showProductsByBuyer(query[1]);
                break;
            case "/findPersonsByProductTitle":
                HibernateSessionFactoryUtil.findBuyersByProductTitle(query[1]);
                break;
            case "/removePerson":
                HibernateSessionFactoryUtil.removeBuyer(query[1]);
                break;
            case "/removeProduct":
                HibernateSessionFactoryUtil.removeProduct(query[1]);
                break;
            case "/buy":
                HibernateSessionFactoryUtil.buy(query[1], query[2]);
                break;
            case "/addPerson":
                HibernateSessionFactoryUtil.addPerson(query[1]);
                break;
            default:
                System.out.println("Команда не распознана");
        }

    }
}