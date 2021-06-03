package com.epam.collections.inventory_processor;

import java.util.Objects;

/**
 * Class that stores data about product
 */
public class Product implements Comparable<Product> {
    private int code;
    private String title;

    public Product(int code, String title) {
        this.title = title;
        this.code = code;

    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Product product) {
        return this.getTitle().compareTo(product.getTitle());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return code == product.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", title='" + title + "'}";
    }
}