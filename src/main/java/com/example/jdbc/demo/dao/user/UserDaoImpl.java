package com.example.jdbc.demo.dao.user;


import com.example.jdbc.demo.model.User;
import com.example.jdbc.demo.model.dbmapper.UserMapper;
import com.example.jdbc.demo.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserResult> getAll() {
        String sql = "SELECT * FROM users ORDER BY id ASC";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public UserResult getUserById(int id) {
        String sql2 = "SELECT * FROM users WHERE id = ?";
//        String sql2 = "select * from users left join product on users.id = product.id_user where users.id = ?";
        return jdbcTemplate.queryForObject(sql2, new Object[]{id}, new UserMapper());
    }

    @Override
    public List<UserResult> getUserByIdList(int id) {
        String sql2 = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(sql2, new Object[]{id}, new UserMapper());
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user (username, fullname, email, password, nomor_handphone)";
        sql += " VALUES (?, ?, ?, ?, ?)";
        Object[] userData = {
                user.getUsername(),
                user.getFullname(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber()};
        jdbcTemplate.update(sql, userData);
    }

    @Override
    public void updateUser(User user, int id) {
        int a = getRandomNumber();
        String sql = "UPDATE user SET username=?, fullname=?, email=?, nomor_handphone=?, password=?";
        sql += "WHERE id=?";
        Object[] userData = {
                user.getUsername(),
                user.getFullname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword()
        };
        jdbcTemplate.update(sql, userData);

    }

    @Override
    public void deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    private int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(50);
    }

    @Override
    public int lastestInput() {
        String sql = "SELECT currval(pg_get_serial_sequence('Users','id'))";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Integer getCountProduct(int id, String param) {
        String sql = "select count(*) from users left join product on users.id = product.id_user where product.id_user = ? and product.status = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id, param}, Integer.class);
        return count;
    }

    @Override
    public UserResult loginUser(User userLogin) {
        String sql = "select * from users where username = ? and password = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userLogin.getUsername(), userLogin.getPassword()}, new UserMapper());
    }

    @Override
    public UserResult loginFacebook(User user) {
        String sql = "select * from users where email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{user.getEmail()}, new UserMapper());
        } catch (Exception e) {
            // Register when jdbcTemplate not get 1 data or on data bound
            user.setUsername(user.getFullname().toLowerCase().trim().replace(" ", ""));
            user.setPassword("ramana1");
            user.setPhoneNumber("085999999999");
            return registerFacebookData(user);
        }
    }

    private UserResult registerFacebookData(User user) {
        String sql = "INSERT INTO users (username, fullname, email, password, phonenumber)";
        sql += " VALUES (?, ?, ?, ?, ?)";
        Object[] userData = {
                user.getUsername(),
                user.getFullname(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber()
        };
        jdbcTemplate.update(sql, userData);
        return getUserById(lastestInput());
    }
}