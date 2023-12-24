package bo.custom;

import Dto.OrderDetailDto;
import bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBo extends SuperBo {
    List<OrderDetailDto> getItems(String value) throws SQLException, ClassNotFoundException;
}
