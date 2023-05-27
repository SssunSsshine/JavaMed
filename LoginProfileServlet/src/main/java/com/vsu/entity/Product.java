package com.vsu.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long idProfile;

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, Long idProfile) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idProfile = idProfile;
    }

    public Product(String name, BigDecimal price, Long idProfile) {
        this.name = name;
        this.price = price;
        this.idProfile = idProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getPrice(), product.getPrice()) && Objects.equals(getIdProfile(), product.getIdProfile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getIdProfile());
    }
}
