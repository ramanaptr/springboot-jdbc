package com.example.jdbc.demo.controller;

import com.example.jdbc.demo.model.Product;
import com.example.jdbc.demo.model.UserResult;
import com.example.jdbc.demo.model.User;
import com.example.jdbc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserControllers {

    private static final String USER = "/user";
    private static final String PRODUCT = "/product";

    @Autowired
    private UserService userService;

    @GetMapping(USER + "={id}")
    public ResponseEntity<UserResult> getById(@PathVariable("id") int id) {
        UserResult userResult = userService.getUserById(id);
        List<Product> product = userService.getProductWithIdUser(userResult.getId());
        userResult.setProduct(product);
        return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @GetMapping(USER + PRODUCT)
    public ResponseEntity<Map<String, Integer>> getCountProductByUserId(@RequestParam int userId, @RequestParam String status) {
        Integer countProduct = userService.getCountProduct(userId, status);
        Map<String, Integer> map = new HashMap<>();
        switch (status) {
            case "Available":
                map.put("Product Available", countProduct);
                return new ResponseEntity<>(map, HttpStatus.OK);
            case "Sold":
                map.put("Product Sold", countProduct);
                return new ResponseEntity<>(map, HttpStatus.OK);
            default:
                map.put("Couldn't find with '" + status + "', Only Available or Sold", 400);
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(USER)
    public ResponseEntity<List<UserResult>> getAll() {
        List<UserResult> listUser = userService.getAll();
        for (UserResult userResult : listUser) {
            List<Product> product = userService.getProductWithIdUser(userResult.getId());
            userResult.setProduct(product);
        }
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @PostMapping(USER + "/login")
    public ResponseEntity<UserResult> loginUser(@RequestBody User user) {
        UserResult userResult = userService.loginUser(user);
        return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @PostMapping(USER + "/facebook")
    public ResponseEntity<UserResult> loginFacebook(@RequestBody User user) {
        UserResult userResult = userService.loginFacebook(user);
        return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @PostMapping(USER + "/register")
    public ResponseEntity<UserResult> registerUser(@RequestBody User user) {
        userService.addUser(user);
        UserResult userResult = userService.getUserById(userService.lastestInput());
        return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @PutMapping(USER)
    public ResponseEntity<UserResult> updateUser(@RequestParam int id, @RequestBody User user) {
        userService.updateUser(user, id);
        UserResult userResult = userService.getUserById(id);
        return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @DeleteMapping(USER)
    public ResponseEntity<UserResult> deleteUser(@RequestParam int id) {
        UserResult userResult = userService.getUserById(id);
        userService.deleteUserById(id);
        return new ResponseEntity<>(userResult, HttpStatus.OK);
    }
}