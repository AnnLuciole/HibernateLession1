package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "buyers", indexes = {@Index(columnList = "name", name = "name_index")})
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, columnDefinition = "TEXT", nullable = false)
    private String name;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Purchase> allPurchases;

    public Buyer(String name) {
        this.name = name;
    }

    public Buyer() {
    }

    @Override
    public String toString() {
        return "Buyer " + name;
    }
}
