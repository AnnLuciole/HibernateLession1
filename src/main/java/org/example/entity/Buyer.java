package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "purchases",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> allProducts;

    public Buyer(String name) {
        this.name = name;
        allProducts = new ArrayList<>();
    }

    public Buyer() {
        allProducts = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Buyer " + name;
    }
}
