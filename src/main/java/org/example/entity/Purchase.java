package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@Entity
@Table(name = "purchases")
public class Purchase {

    @EmbeddedId
    MyId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id", insertable=false, updatable=false)
    Buyer buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
    Product product;

    @Column(name = "product_cost")
    double cost;

    public Purchase(){
        this.setId(new MyId());
    }

    @Getter
    @Setter
    @Embeddable
    @NoArgsConstructor
    public static class MyId implements Serializable {
        @Column(name = "buyer_id", insertable=false, updatable=false)
        Long buyerId;

        @Column(name = "product_id", insertable=false, updatable=false)
        Long productId;

        public MyId (Long buyerId, Long productId){
            this.buyerId = buyerId;
            this.productId = productId;
        }

        @Override
        public int hashCode() {
            return (int) (buyerId + productId);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            MyId id = (MyId) obj;
            return buyerId.equals(id.getBuyerId()) && productId.equals(id.getProductId());
        }
    }

    public Purchase(Buyer buyer, Product product){
        this.buyer = buyer;
        this.product = product;
        this.cost = product.getCost();
        this.setId(new MyId(buyer.getId(), product.getId()));
    }

    @Override
    public String toString() {
        return buyer + " bought the " + product + " for " + cost;
    }

    public MyId getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Product getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }

    public void setId(MyId id) {
        this.id = id;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCost(double cost) {
        this.cost = product.getCost();
    }
}
