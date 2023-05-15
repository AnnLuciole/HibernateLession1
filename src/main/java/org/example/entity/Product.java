package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "products", indexes = {@Index(columnList = "title", name = "title_index")})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true, columnDefinition = "TEXT", nullable = false)
    private String title;

    @Column(name = "cost")
    private double cost;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Purchase> allPurchases;

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(String title) {
        this.title = title;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product " + title;
    }
}
