package dao.custom.impl;

import DB.DBConnection;
import Dto.OrderDetailDto;
import Dto.OrderDto;
import dao.DaoFactory;
import dao.custom.OrderDao;
import dao.custom.OrderDetailDao;
import dao.util.CrudUtil;
import dao.util.DaoType;
import dao.util.HibernateUtil;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {


    @Override
    public boolean orderSave(OrderDto dto) throws SQLException {
//        OrderDetailDao orderDetailDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAIL);
//
//        Connection connection=null;
//        try{
//            String sql="INSERT INTO orders VALUES(?,?,?)";
//            connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            pstm.setString(1,dto.getOrderId());
//            pstm.setString(2,dto.getDate());
//            pstm.setObject(3, dto.getCustId());
//
//            connection.setAutoCommit(false);
//
//            if (pstm.executeUpdate()>0){
//                boolean isDetailsSaved= orderDetailDao.orderDetailSave(dto.getDetailDtoList());
//                if (isDetailsSaved){
//                    connection.commit();
//                    return true;
//                }
//            }
//        }catch (SQLException | ClassNotFoundException ex){
//            connection.rollback();
//        }finally {
//            connection.setAutoCommit(true);
//        }
//        return false;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Orders order=new Orders(
                dto.getOrderId(),
                dto.getDate()
        );
        order.setCustomer(session.find(Customer.class,dto.getCustId()));

        session.save(order);


        for (OrderDetailDto detailDto:dto.getDetailDtoList()) {
            OrderDetail orderDetail=new OrderDetail(
                    new OrderDetailKey(detailDto.getOrderId(),detailDto.getItemCode()),
                    order,
                    session.find(Item.class,detailDto.getItemCode()),
                    detailDto.getQty(),
                    detailDto.getUnitPrice()
            );

            session.save(orderDetail);
        }

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public OrderDto getLastId() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM Orders ORDER BY id DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    null
            );
        }else {
            return null;
        }

    }

    @Override
    public List<Orders> allOrders() throws SQLException, ClassNotFoundException {
//        String sql="SELECT * FROM Orders";
//        ResultSet resultSet=CrudUtil.execute(sql);
//
//        List<Orders> entityList=new ArrayList<>();
//
//        while (resultSet.next()){
//            entityList.add(
//                    new Orders(
//                            resultSet.getString(1),
//                            resultSet.getString(2),
//                            resultSet.getString(3)
//                    )
//            );
//
//        }
//
//        return entityList;
        return null;
    }

//    ------------------------------crud dao methods------------------------------

    @Override
    public boolean save(OrderDto entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDto entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
