package bo.custom;

import Dto.CustomerDto;
import bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo<T,dataType> extends SuperBo {
    boolean saveCustomer(T dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(dataType value) throws SQLException, ClassNotFoundException;

    List<T> allCustomers() throws SQLException, ClassNotFoundException;
}
