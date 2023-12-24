package dao.custom;

import Dto.OrderDto;
import dao.CrudDao;
import entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends CrudDao<OrderDto,String> {
    public boolean orderSave(OrderDto dto) throws SQLException, ClassNotFoundException;

    public OrderDto getLastId() throws SQLException, ClassNotFoundException;

    List<Orders> allOrders() throws SQLException, ClassNotFoundException;
}
