package dao;

import entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T,dataType> extends SuperDao{
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity);
    boolean delete(dataType value) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;
}
