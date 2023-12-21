package dao.custom;

import Dto.OrderDetailDto;
import dao.CrudDao;
import entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao extends CrudDao<OrderDetail,String> {
    public boolean orderDetailSave(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException;
}
