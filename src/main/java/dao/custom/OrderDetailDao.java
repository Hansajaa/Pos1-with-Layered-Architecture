package dao.custom;

import Dto.OrderDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao {
    public boolean orderDetailSave(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException;
}
