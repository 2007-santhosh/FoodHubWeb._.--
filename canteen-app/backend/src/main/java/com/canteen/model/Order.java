package com.canteen.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(length = 2000)
    private String summary;

    private double total;

    private Instant createdAt = Instant.now();

    private String status = "PLACED";

    private String paymentMethod = "CASH";

    private String paymentStatus = "PENDING";

    public Order() {
    }

    public Order(String username,
                 String summary,
                 double total) {

        this.username = username;
        this.summary = summary;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSummary() {
        return summary;
    }

    public double getTotal() {
        return total;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}