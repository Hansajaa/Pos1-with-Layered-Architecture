package dao.custom.impl;

import DB.DBConnection;
import Dto.CustomerDto;
import dao.custom.CustomerDao;
import dao.util.CrudUtil;
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
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        pstm.setString(1,entity.getId());
//        pstm.setString(2,entity.getName());
//        pstm.setString(3, entity.getAddress());
//        pstm.setDouble(4,entity.getSalary());
//
//        return pstm.executeUpdate()>0;
        return CrudUtil.execute(sql,entity.getId(),entity.getName(),entity.getAddress(),entity.getSalary());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Customer set name=?, address=?, salary=? WHERE id=?";
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        if (entity!=null){
//            pstm.setString(1, entity.getName());
//            pstm.setString(2,entity.getAddress());
//            pstm.setDouble(3,entity.getSalary());
//            pstm.setString(4,entity.getId());
//        }
//        return pstm.executeUpdate()>0;
        return CrudUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getSalary(),entity.getId());
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE id=?";
//        Statement stm = DBConnection.getInstance().getConnection().createStatement();
//
//        return stm.executeUpdate(sql)>0;
        return CrudUtil.execute(sql,value);
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> customerList=new ArrayList<>();
        String sql="SELECT * FROM Customer";
//        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()){
            Customer customer=new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3), resultSet.getDouble(4));
            customerList.add(customer);
        }

        return customerList;

    }
}
