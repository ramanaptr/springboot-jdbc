package com.example.jdbc.demo.controller;

import com.example.jdbc.demo.model.Product;
import com.example.jdbc.demo.model.UserResult;
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
public class ProductController {

    private static final String USER = "/user";
    private static final String PRODUCT = "/product";

    @Autowired
    private UserService userService;

    @GetMapping(PRODUCT)
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> productList = userService.getAllProduct();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping(PRODUCT)
    public ResponseEntity<Map<String, Boolean>> addProduct(@RequestBody Product p) {
        boolean isExist = userService.addProduct(p);

        Map<String, Boolean> map = new HashMap<>();
        if (isExist) {
            map.put("Successful", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("Successful", false);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(PRODUCT + "/")
    public ResponseEntity<List<Product>> getProductByIdUser(@RequestParam int userId) {
        UserResult userResult = userService.getUserById(userId);
        List<Product> product = userService.getProductWithIdUser(userResult.getId());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
