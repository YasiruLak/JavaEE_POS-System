package lk.ijse.pos.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;

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
public interface ItemBO extends SuperBO {

    boolean addItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    ObservableList<ItemDTO> getAllItem(Connection connection) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String itemCode, Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteItem(Connection connection, String itemCode) throws SQLException, ClassNotFoundException;

    boolean updateItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
}
