package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {
    public boolean save(CustomerDto dto) throws SQLException, IOException, ClassNotFoundException;
    public CustomerDto finById(String id) throws SQLException, IOException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, IOException, ClassNotFoundException;
    public boolean update(CustomerDto dto) throws SQLException, IOException, ClassNotFoundException;
    public List<CustomerDto> search(String searchText) throws SQLException, ClassNotFoundException;
}
