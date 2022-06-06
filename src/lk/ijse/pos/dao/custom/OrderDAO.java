package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/20/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public interface OrderDAO extends CrudDAO<Orders, String, Connection> {

    boolean ifOrderExist(String oid, Connection connection) throws SQLException, ClassNotFoundException;

    String generateNewOrderId(Connection connection) throws SQLException, ClassNotFoundException;

    int ordersCount(Connection connection) throws SQLException, ClassNotFoundException;
}
