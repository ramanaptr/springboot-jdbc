package com.example.jdbc.demo.service;

import com.example.jdbc.demo.model.Product;
import com.example.jdbc.demo.model.User;
import com.example.jdbc.demo.model.UserResult;

import java.util.List;

public interface UserService {
    // User
    List<UserResult> getAll();

    UserResult getUserById(int id);

    Integer getCountProduct(int id, String param);

    List<UserResult> getUserByIdList(int id);

    void addUser(User user);

    void updateUser(User user, int id);

    void deleteUserById(int id);

    int lastestInput();

    // Product
    List<Product> getProductWithIdUser(int id_user);

    boolean addProduct(Product product);

    List<Product> getAllProduct();

    UserResult loginUser(User userLogin);

    UserResult loginFacebook(User userLogin);
}
