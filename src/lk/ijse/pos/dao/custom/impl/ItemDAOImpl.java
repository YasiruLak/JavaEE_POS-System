package lk.ijse.pos.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/17/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public class ItemDAOImpl implements ItemDAO {


    @Override
    public boolean add(Item item, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "Insert into Item values(?,?,?,?)",item.getItemCode(),
                item.getName(),item.getQtyOnHand(),item.getPrice());
    }

    @Override
    public boolean delete(String code, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"Delete from Item where itemCode=?",code);
    }

    @Override
    public boolean update(Item item, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "UPDATE Item SET name=?,qtyOnHand=?,price=? WHERE itemCode=?", item.getName(),
                item.getQtyOnHand(), item.getPrice(), item.getItemCode());
    }

    @Override
    public Item search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT * FROM Item WHERE itemCode =?", code);
        if (resultSet.next()){
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
        }else {
            return null;
        }
    }

    @Override
    public ObservableList<Item> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT * FROM Item");

        ObservableList<Item> obList = FXCollections.observableArrayList();

        while (resultSet.next()){
            Item item = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );

            obList.add(item);

        }

        return obList;
    }

    @Override
    public boolean updateQtyOnHand(Connection connection, String id, int qty) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeUpdate(connection, "UPDATE Item SET qtyOnHand=(qtyOnHand - "+ qty +")WHERE itemCode=?", id);
    }
}
