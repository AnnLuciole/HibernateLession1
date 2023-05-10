package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "cost")
    private double cost;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "purchases",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "buyer_id")
    )
    private List<Buyer> allBuyers;

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
        allBuyers = new ArrayList<>();
    }

    public Product(String title) {
        this.title = title;
        allBuyers = new ArrayList<>();
    }

    public Product() {
        allBuyers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Product " + title;
    }
}
