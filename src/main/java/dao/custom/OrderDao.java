package dao.custom;

import Dto.OrderDto;
import dao.CrudDao;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<OrderDto,String> {
    public boolean orderSave(OrderDto dto) throws SQLException, ClassNotFoundException;

    public OrderDto getLastId() throws SQLException, ClassNotFoundException;
}
