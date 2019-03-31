package com.example.jdbc.demo.service;

import com.example.jdbc.demo.dao.product.ProductDao;
import com.example.jdbc.demo.dao.user.UserDao;
import com.example.jdbc.demo.model.Product;
import com.example.jdbc.demo.model.User;
import com.example.jdbc.demo.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    // User Service
    @Override
    public List<UserResult> getAll() {
        return userDao.getAll();
    }

    @Override
    public UserResult getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public Integer getCountProduct(int id, String param) {
        return userDao.getCountProduct(id, param);
    }

    @Override
    public List<UserResult> getUserByIdList(int id) {
        return userDao.getUserByIdList(id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user, int id) {
        userDao.updateUser(user, id);
    }

    @Override
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    @Override
    public int lastestInput() {
        return userDao.lastestInput();
    }

    // Product serivce
    @Override
    public List<Product> getProductWithIdUser(int id_user) {
        return productDao.getProductWithIdUser(id_user);
    }

    @Override
    public boolean addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    @Override
    public UserResult loginUser(User userLogin) {
        return userDao.loginUser(userLogin);
    }

    @Override
    public UserResult loginFacebook(User userLogin) {
        return userDao.loginFacebook(userLogin);
    }
}
