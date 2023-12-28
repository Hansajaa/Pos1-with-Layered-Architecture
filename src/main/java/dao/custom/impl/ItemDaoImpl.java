package dao.custom.impl;

import DB.DBConnection;
import Dto.ItemDto;
import dao.custom.ItemDao;
import dao.util.CrudUtil;
import dao.util.HibernateUtil;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;

//        String sql = "INSERT INTO item VALUES(?,?,?,?)";

//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        pstm.setString(1, entity.getCode());
//        pstm.setString(2, entity.getDescription());
//        pstm.setDouble(3,entity.getUnitPrice());
//        pstm.setInt(4,entity.getQtyOnHand());
//
//        return pstm.executeUpdate()>0;

//        return CrudUtil.execute(sql,entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());

    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
//        String sql = "UPDATE item set description=?, unitPrice=?, qtyOnHand=? WHERE code=?";
//
//        return CrudUtil.execute(sql,entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Item item = session.find(Item.class, entity.getCode());
        item.setDescription(entity.getDescription());
        item.setQtyOnHand(entity.getQtyOnHand());
        item.setUnitPrice(entity.getUnitPrice());
        session.save(item);

        transaction.commit();
        session.close();

        return true;

    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Item.class,value));
        transaction.commit();
        session.close();

        return true;

    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
//
//        List<Item> itemList = new ArrayList<>();
//        String sql="SELECT * FROM item";
//        Statement stm = DBConnection.getInstance().getConnection().createStatement();
//        ResultSet resultSet = CrudUtil.execute(sql);
//        while (resultSet.next()){
//            Item item=new Item(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4));
//            itemList.add(item);



//        }

//        return itemList;

        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Item ");
        List<Item> list = query.list();

        return list;
    }
//
//    @Override
//    public ItemDto searchItem(String code) {
//        return null;
//    }
//
//    @Override
//    public boolean updateItem(ItemDto dto) {
//        return false;
//    }
}
