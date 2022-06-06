package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
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
public interface ItemDAO extends CrudDAO<Item, String, Connection> {

    boolean updateQtyOnHand(Connection connection, String id, int qty) throws SQLException, ClassNotFoundException;

    int itemCount(Connection connection) throws SQLException, ClassNotFoundException;
}
