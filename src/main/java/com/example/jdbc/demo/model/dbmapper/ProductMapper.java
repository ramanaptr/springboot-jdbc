package com.example.jdbc.demo.model.dbmapper;

import com.example.jdbc.demo.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setIdUser(resultSet.getInt("id_user"));
        product.setProductName(resultSet.getString("productName"));
        product.setStatus(resultSet.getString("status"));
        return product;
    }
}
