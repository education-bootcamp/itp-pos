package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ProductDao;
import entity.Customer;
import entity.Product;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findAll(String searchText) throws SQLException, ClassNotFoundException {
        searchText= "%"+searchText+"%";
        return CrudUtil.execute("SELECT * FROM product WHERE description LIKE ?",  searchText);
    }

    @Override
    public boolean create(Product entity) throws IOException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO product VALUES(?,?,?,?)", entity.getId(),entity.getQty(), entity.getUnitPrice(), entity.getDescription());
    }

    @Override
    public boolean update(Product entity) throws IOException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE product SET description=?, qty=?, unit_price=? WHERE id=?", entity.getDescription(), entity.getQty(), entity.getUnitPrice(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws IOException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM product WHERE id=?", id);
    }

    @Override
    public Product findById(String s) throws IOException, SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product WHERE id=?", s);
        if(resultSet.next()){
            return new Product(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    @Override
    public List<Product> findAll() throws IOException, SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product WHERE id=?");
        ArrayList<Product> products = new ArrayList<>();
        while (resultSet.next()){
            products.add( new Product(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4)
            ));
        }
        return products;
    }
}
