package com.example.jdbc.demo.dao.user;

import com.example.jdbc.demo.model.User;
import com.example.jdbc.demo.model.UserResult;

import java.util.List;

public interface UserDao {
    List<UserResult> getAll();

    UserResult getUserById(int id);

    List<UserResult> getUserByIdList(int id);

    void addUser(User user);

    void updateUser(User user, int id);

    void deleteUserById(int id);

    int lastestInput();

    Integer getCountProduct(int id, String param);

    UserResult loginUser(User userLogin);

    UserResult loginFacebook(User userLogin);
}