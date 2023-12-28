package dao.custom.impl;

import dao.custom.CustomerDao;
import dao.util.CrudUtil;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {

        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
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
