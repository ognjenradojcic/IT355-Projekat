package com.ognjen.projekat.repository.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer productId;
    private String name;
    private String manufacturer;
    private Double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<OrderEntity> orderEntities;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    public ProductEntity() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Collection<OrderEntity> getOrders() {
        return orderEntities;
    }

    public void setOrders(Collection<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }

    public CategoryEntity getCategory() {
        return categoryEntity;
    }

    public void setCategory(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
