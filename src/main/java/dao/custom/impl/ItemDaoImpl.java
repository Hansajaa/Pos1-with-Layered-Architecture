package dao.custom.impl;

import DB.DBConnection;
import Dto.ItemDto;
import dao.custom.ItemDao;
import dao.util.CrudUtil;
import entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO item VALUES(?,?,?,?)";

//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        pstm.setString(1, entity.getCode());
//        pstm.setString(2, entity.getDescription());
//        pstm.setDouble(3,entity.getUnitPrice());
//        pstm.setInt(4,entity.getQtyOnHand());
//
//        return pstm.executeUpdate()>0;

        return CrudUtil.execute(sql,entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());

    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE code = ?";
//        Statement stm = DBConnection.getInstance().getConnection().createStatement();
//
//        return stm.executeUpdate(sql)>0;
        return CrudUtil.execute(sql,value);
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {

        List<Item> itemList = new ArrayList<>();
        String sql="SELECT * FROM item";
//        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            Item item=new Item(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4));
            itemList.add(item);
        }

        return itemList;
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
