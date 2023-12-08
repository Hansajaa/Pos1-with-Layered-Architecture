package bo.custom.impl;

import Dto.CustomerDto;
import bo.custom.CustomerBo;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo<CustomerDto,String> {

    CustomerDao dao=new CustomerDaoImpl();
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {

        return dao.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()));
    }

    @Override
    public boolean deleteCustomer(String value) throws SQLException, ClassNotFoundException {
        return dao.delete(value);
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<CustomerDto> dtoList=new ArrayList<>();

        List<Customer> allCustomers = dao.getAll();

        for (Customer customer:allCustomers) {
            dtoList.add(
              new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary())
            );
        }
        return dtoList;
    }
}
