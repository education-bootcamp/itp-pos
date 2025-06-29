package bo.custom.impl;

import bo.custom.ProductBo;
import dao.DaoFactory;
import dao.DaoType;
import dao.custom.ProductDao;
import dto.ProductDto;
import entity.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductBoImpl implements ProductBo {
    private ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
    @Override
    public boolean save(ProductDto dto) throws SQLException, IOException, ClassNotFoundException {
        return productDao.create(toProduct(dto));
    }

    @Override
    public ProductDto finById(String id) throws SQLException, IOException, ClassNotFoundException {
        return toProductDto(productDao.findById(id));
    }

    @Override
    public boolean delete(String id) throws SQLException, IOException, ClassNotFoundException {
        return productDao.delete(id);
    }

    @Override
    public boolean update(ProductDto dto) throws SQLException, IOException, ClassNotFoundException {
        return productDao.update(toProduct(dto));
    }

    @Override
    public List<ProductDto> search(String searchText) throws SQLException, ClassNotFoundException {
        return productDao.findAll(searchText).stream().map(this::toProductDto).collect(Collectors.toList());
    }

    private ProductDto toProductDto(Product product){
        if(product == null){
            return null;
        }
        return new ProductDto(product.getId(), product.getQty(), product.getUnitPrice(), product.getDescription());
    }

    private Product toProduct(ProductDto productDto){
        if(productDto == null){
            return null;
        }
        return new Product(
                productDto.getId(), productDto.getQty(), productDto.getUnitPrice(), productDto.getDescription()
        );
    }

}
