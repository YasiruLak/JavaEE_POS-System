package lk.ijse.pos.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/20/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Orders orders, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"INSERT INTO Orders VALUES (?,?,?,?,?,?,?)",orders.getOrderId(),
                orders.getcId(), orders.getOrderDate(), orders.getTime(), orders.getTotal(), orders.getDiscount(),
                orders.getSubTotal());
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders orders, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Orders search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Orders> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean ifOrderExist(String oid, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewOrderId(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1;");
        return resultSet.next() ? String.format("OD%03d", (Integer.parseInt(resultSet.getString("orderId").replace("OD", "")) + 1)) : "OD001";
    }
}
