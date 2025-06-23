package dao.custom;

import dao.CrudDao;
import entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao extends CrudDao<Customer, String> {
    public List<Customer> findAll(String searchText) throws SQLException, ClassNotFoundException;
}
