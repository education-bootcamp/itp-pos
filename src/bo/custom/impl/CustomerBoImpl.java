package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.DaoType;
import dao.custom.CustomerDao;
import dto.CustomerDto;
import entity.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean save(CustomerDto dto) throws SQLException, IOException, ClassNotFoundException {
        return customerDao.create(
                new Customer(dto.getId(),dto.getName(),dto.getAddress(), dto.getSalary())
        );
    }

    @Override
    public CustomerDto finById(String id) throws SQLException, IOException, ClassNotFoundException {
        Customer cus = customerDao.findById(id);
        return cus!=null?new CustomerDto(
                cus.getId(),cus.getName(),cus.getAddress(),cus.getSalary()
        ):null;
    }

    @Override
    public boolean delete(String id) throws SQLException, IOException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException, IOException, ClassNotFoundException {
        return customerDao.update(
                new Customer(dto.getId(),dto.getName(),dto.getAddress(), dto.getSalary())
        );
    }

    @Override
    public List<CustomerDto> search(String searchText) throws SQLException, ClassNotFoundException {
        List<Customer> all = customerDao.findAll(searchText);
        List<CustomerDto> dtos = new ArrayList<>();
        for (Customer customer : all) {
            dtos.add(new CustomerDto(
                    customer.getId(),customer.getName(), customer.getAddress(),customer.getSalary()
            ));
        }
        return dtos;
    }
}
