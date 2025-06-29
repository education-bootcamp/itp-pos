package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;
import dto.ProductDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductBo extends SuperBo {
    public boolean save(ProductDto dto) throws SQLException, IOException, ClassNotFoundException;
    public ProductDto finById(String id) throws SQLException, IOException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, IOException, ClassNotFoundException;
    public boolean update(ProductDto dto) throws SQLException, IOException, ClassNotFoundException;
    public List<ProductDto> search(String searchText) throws SQLException, ClassNotFoundException;
}
