package bo.custom.impl;

import Dto.OrderDetailDto;
import bo.custom.OrderDetailBo;
import dao.DaoFactory;
import dao.custom.OrderDetailDao;
import dao.util.DaoType;
import entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailBoImpl implements OrderDetailBo {

    OrderDetailDao dao= DaoFactory.getInstance().getDao(DaoType.ORDER_DETAIL);

    @Override
    public List<OrderDetailDto> getItems(String value) throws SQLException, ClassNotFoundException {
        List<OrderDetail> entities = dao.getItems(value);

        List<OrderDetailDto> dtos =new ArrayList<>();

        for (OrderDetail entity:entities) {
            dtos.add(
              new OrderDetailDto(
                      entity.getOrderId(),
                      entity.getItemCode(),
                      entity.getQty(),
                      entity.getUnitPrice()
              )
            );
        }

        return dtos;
    }
}
