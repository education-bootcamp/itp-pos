package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDao;
import entity.Customer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean create(Customer entity) throws IOException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTo customer VALUES(?,?,?,?)",
                entity.getId(), entity.getName(), entity.getAddress(), entity.getSalary());
    }

    @Override
    public boolean update(Customer entity) throws IOException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET name = ?, address = ?, salary = ? WHERE id = ?",
                entity.getName(), entity.getAddress(), entity.getSalary(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws IOException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE id=?",id);
    }

    @Override
    public Customer findById(String s) throws IOException, SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT customer WHERE id=?", s);
        if(set.next()){
            return new Customer(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public List<Customer> findAll() throws IOException, SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM customer");
        List<Customer> dataList = new ArrayList<>();
        while(set.next()){
            dataList.add(new Customer(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4)
            ));
        }
        return dataList;
    }

    @Override
    public List<Customer> findAll(String searchText) throws SQLException, ClassNotFoundException {
        List<Customer> dataList = new ArrayList<>();
        searchText= "%"+searchText+"%";
        ResultSet set = CrudUtil.execute("SELECT * FROM customer WHERE name LIKE ? OR address LIKE ?",searchText,searchText);
        while(set.next()){
            dataList.add(new Customer(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4)
            ));
        }
        return dataList;
    }
}
