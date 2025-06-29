package dao.custom;

import dao.CrudDao;
import entity.Customer;
import entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends CrudDao<Product, String> {
    public List<Product> findAll(String searchText) throws SQLException, ClassNotFoundException;
}
