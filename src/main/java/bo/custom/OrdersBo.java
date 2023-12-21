package bo.custom;

import bo.SuperBo;

import java.sql.SQLException;

public interface OrdersBo<T,dataType> extends SuperBo {
    dataType getLastId() throws SQLException, ClassNotFoundException;

    boolean saveOrder(T dto) throws SQLException, ClassNotFoundException;
}
