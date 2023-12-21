package bo.custom.impl;

import Dto.CustomerDto;
import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;
import dao.util.DaoType;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo<CustomerDto,String> {

    CustomerDao dao= DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {

        return dao.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()));
    }

    @Override
    public boolean deleteCustomer(String value) throws SQLException, ClassNotFoundException {
        return dao.delete(value);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        if (dto!=null){
            return dao.update(
                    new Customer(
                            dto.getId(),
                            dto.getName(),
                            dto.getAddress(),
                            dto.getSalary()
                    )
            );
        }else {
            return false;
        }
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
