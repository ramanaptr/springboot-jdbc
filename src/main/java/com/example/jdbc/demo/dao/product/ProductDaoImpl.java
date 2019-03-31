package com.example.jdbc.demo.dao.product;

import com.example.jdbc.demo.dao.user.UserDao;
import com.example.jdbc.demo.model.Product;
import com.example.jdbc.demo.model.UserResult;
import com.example.jdbc.demo.model.dbmapper.ProductMapper;
import com.example.jdbc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Product> getProductWithIdUser(int id_user) {
        String sql = "SELECT * FROM product WHERE id_user = ?";
        return jdbcTemplate.query(sql, new Object[]{id_user}, new ProductMapper());
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO product (id_user, productName, status) VALUES (?, ?, ?) ";
        ProductPresenter presenter = new ProductPresenterImpl();
        boolean isUserExist = presenter.getUserExist(product, userDao.getUserByIdList(product.getIdUser()));
        if (isUserExist) jdbcTemplate.update(sql, product.getIdUser(), product.getProductName(), product.getStatus());
        return isUserExist;
    }

    @Override
    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM product";
        List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
        return list;
    }

    private interface ProductPresenter {
        boolean getUserExist(Product p, List<UserResult> userById);
    }


    private class ProductPresenterImpl implements ProductPresenter {
        @Override
        public boolean getUserExist(Product product, List<UserResult> userById) {
            boolean isExist = userById.size() != 0;
            return isExist;
        }
    }
}
