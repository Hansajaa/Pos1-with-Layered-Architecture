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
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.find(Customer.class, entity.getId());
        customer.setName(entity.getName());
        customer.setAddress(entity.getAddress());
        customer.setSalary(entity.getSalary());

        session.save(customer);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.find(Customer.class, value);
        session.delete(customer);
        transaction.commit();

        session.close();
        return true;
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
