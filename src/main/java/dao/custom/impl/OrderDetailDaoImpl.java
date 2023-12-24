package dao.custom.impl;

import DB.DBConnection;
import Dto.OrderDetailDto;
import dao.custom.OrderDetailDao;
import dao.util.CrudUtil;
import entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean orderDetailSave(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException {
        boolean orderDetailsSaved= true;

        Connection connection = DBConnection.getInstance().getConnection();

        for (OrderDetailDto detailDto:list) {
            String sql="INSERT INTO OrderDetail VALUES(?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,detailDto.getOrderId());
            pstm.setString(2,detailDto.getItemCode());
            pstm.setInt(3,detailDto.getQty());
            pstm.setDouble(4,detailDto.getUnitPrice());
//            boolean isSaved = CrudUtil.execute(sql,detailDto.getOrderId(),detailDto.getItemCode(),detailDto.getQty(),detailDto.getUnitPrice());

            if (!(pstm.executeUpdate()>0)){
                orderDetailsSaved=false;
            }
        }

        return orderDetailsSaved;
    }

    @Override
    public List<OrderDetail> getItems(String value) throws SQLException, ClassNotFoundException {

        String sql="SELECT * FROM orderdetail WHERE orderId=?";

        ResultSet resultSet = CrudUtil.execute(sql,value);

        List<OrderDetail> orderDetailList=new ArrayList<>();

        while (resultSet.next()){
            orderDetailList.add(
                    new OrderDetail(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getDouble(4)
                    )
            );
        }

        return orderDetailList;

    }

    //    ----------------------------crud dao methods----------------------------------------------
    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
