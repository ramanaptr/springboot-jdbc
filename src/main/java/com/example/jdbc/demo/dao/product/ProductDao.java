package com.example.jdbc.demo.dao.product;

import com.example.jdbc.demo.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProductWithIdUser(int id_user);

    boolean addProduct(Product product);

    List<Product> getAllProduct();
}
