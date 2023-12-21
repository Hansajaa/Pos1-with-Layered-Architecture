package bo.custom;

import bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo<T,dataType> extends SuperBo {
    boolean saveItem(T dto) throws SQLException, ClassNotFoundException;
    boolean deleteItem(dataType value) throws SQLException, ClassNotFoundException;
    List<T> allItem() throws SQLException, ClassNotFoundException;
    boolean updateItem(T dto);
}
