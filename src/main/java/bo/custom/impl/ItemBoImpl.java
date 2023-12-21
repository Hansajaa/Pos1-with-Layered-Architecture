package bo.custom.impl;

import Dto.ItemDto;
import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dao.util.DaoType;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo<ItemDto,String> {

    ItemDao itemDao= DaoFactory.getInstance().getDao(DaoType.ITEM);

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        if (dto!=null){
            return itemDao.save(
                    new Item(
                            dto.getCode(),
                            dto.getDescription(),
                            dto.getUnitPrice(),
                            dto.getQuantityOnHand()
                    )
            );
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteItem(String value) throws SQLException, ClassNotFoundException {
        if (value!=null){
            return itemDao.delete(value);
        }else {
            return false;
        }
    }

    @Override
    public List<ItemDto> allItem() throws SQLException, ClassNotFoundException {
        List<Item> entityList=itemDao.getAll();
        List<ItemDto> dtoList=new ArrayList<>();

        for (Item item:entityList) {
            dtoList.add(
                    new ItemDto(
                            item.getCode(),
                            item.getDescription(),
                            item.getUnitPrice(),
                            item.getQtyOnHand()
                    )
            );
        }
        return dtoList;
    }

    @Override
    public boolean updateItem(ItemDto dto) {
        return false;
    }
}
