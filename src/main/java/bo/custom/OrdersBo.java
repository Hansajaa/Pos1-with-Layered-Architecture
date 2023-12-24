package bo.custom;

import Dto.OrderDto;
import bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface OrdersBo<T,dataType> extends SuperBo {
    dataType getLastId() throws SQLException, ClassNotFoundException;

    boolean saveOrder(T dto) throws SQLException, ClassNotFoundException;

    List<OrderDto> allOrders() throws SQLException, ClassNotFoundException;
}
