package dao;

import Dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    CustomerDto searchCustomer(String id);
    boolean updateCustomer(CustomerDto dto);
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException;
}
