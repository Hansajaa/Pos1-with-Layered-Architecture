package bo.custom;

import java.sql.SQLException;

public interface OrdersBo<T,dataType> {
    dataType getLastId() throws SQLException, ClassNotFoundException;

    boolean saveOrder(T dto) throws SQLException, ClassNotFoundException;
}
