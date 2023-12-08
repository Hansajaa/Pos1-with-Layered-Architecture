package dao.custom.impl;

import DB.DBConnection;
import Dto.CustomerDto;
import dao.custom.CustomerDao;
import entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,entity.getId());
        pstm.setString(2,entity.getName());
        pstm.setString(3, entity.getAddress());
        pstm.setDouble(4,entity.getSalary());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Customer entity) {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE id='"+value+"'";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();

        return stm.executeUpdate(sql)>0;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> customerList=new ArrayList<>();
        String sql="SELECT * FROM Customer";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery(sql);

        while (resultSet.next()){
            Customer customer=new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3), resultSet.getDouble(4));
            customerList.add(customer);
        }

        return customerList;
    }
}
