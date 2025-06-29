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
import java.util.stream.Collectors;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean save(CustomerDto dto) throws SQLException, IOException, ClassNotFoundException {
        return customerDao.create(toCustomer(dto));
    }

    @Override
    public CustomerDto finById(String id) throws SQLException, IOException, ClassNotFoundException {
        Customer cus = customerDao.findById(id);
        return cus!=null?toCustomerDto(cus):null;
    }

    @Override
    public boolean delete(String id) throws SQLException, IOException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException, IOException, ClassNotFoundException {
        return customerDao.update(toCustomer(dto));
    }

    @Override
    public List<CustomerDto> search(String searchText) throws SQLException, ClassNotFoundException {

        return customerDao.
                findAll(searchText).stream()
                .map(this::toCustomerDto).collect(Collectors.toList());
    }

    private CustomerDto toCustomerDto(Customer customer){
        if(customer == null){
            return null;
        }
        return new CustomerDto(
                customer.getId(),customer.getName(), customer.getAddress(),customer.getSalary()
        );
    }
    private Customer toCustomer(CustomerDto dto){
        if(dto == null){
            return null;
        }
       return new Customer(dto.getId(),dto.getName(),dto.getAddress(), dto.getSalary());
    }

}
