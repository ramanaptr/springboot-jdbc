package com.example.jdbc.demo.model.dbmapper;

import com.example.jdbc.demo.model.UserResult;
import com.example.jdbc.demo.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserResult> {

    @Override
    public UserResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setFullname(rs.getString("fullname"));

        UserResult userResult = new UserResult();
        userResult.setId(rs.getInt("id"));
        userResult.setUsername(rs.getString("username"));
        userResult.setFullname(rs.getString("fullname"));
        userResult.setEmail(rs.getString("email"));
        userResult.setPhoneNumber(rs.getString("phonenumber"));
        userResult.setPassword(rs.getString("password"));
        return userResult;
    }
}