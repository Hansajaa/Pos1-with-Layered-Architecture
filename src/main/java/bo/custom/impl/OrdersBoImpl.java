package bo.custom.impl;

import Dto.OrderDto;
import bo.custom.OrdersBo;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;

import java.sql.SQLException;

public class OrdersBoImpl implements OrdersBo<OrderDto,String> {

    OrderDao orderDao=new OrderDaoImpl();
    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        OrderDto dto = orderDao.getLastId();
        String lastId = dto.getOrderId();
        int num = Integer.parseInt(lastId.split("[D]")[1]);
        num++;
        String newId=String.format("D%03d",num);
        return newId;
    }

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.orderSave(dto);
    }
}