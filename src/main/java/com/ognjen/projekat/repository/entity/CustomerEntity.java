package com.ognjen.projekat.repository.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Integer customerId;
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Collection<OrderEntity> orderEntities;


    public CustomerEntity() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<OrderEntity> getOrders() {
        return orderEntities;
    }

    public void setOrders(Collection<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }
}
