package lk.ijse.pos.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/17/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Item item = new Item(
                itemDTO.getItemCode(),
                itemDTO.getItemName(),
                itemDTO.getQtyOnHand(),
                itemDTO.getUnitPrice()
        );

        return itemDAO.add(item, connection);
    }

    @Override
    public ObservableList<ItemDTO> getAllItem(Connection connection) throws SQLException, ClassNotFoundException {
        ObservableList<Item> all = itemDAO.getAll(connection);

        ObservableList<ItemDTO> obList = FXCollections.observableArrayList();

        for (Item temp : all){
            ItemDTO itemDTO = new ItemDTO(
                    temp.getItemCode(),
                    temp.getName(),
                    temp.getQtyOnHand(),
                    temp.getPrice()
            );

            obList.add(itemDTO);
        }
        return obList;
    }

    @Override
    public ItemDTO searchItem(String itemCode, Connection connection) throws SQLException, ClassNotFoundException {

        Item item = itemDAO.search(itemCode, connection);

        ItemDTO itemDTO = new ItemDTO(
                item.getItemCode(), item.getName(), item.getQtyOnHand(), item.getPrice()
        );

        return itemDTO;
    }

    @Override
    public boolean deleteItem(Connection connection, String itemCode) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return false;
    }
}
