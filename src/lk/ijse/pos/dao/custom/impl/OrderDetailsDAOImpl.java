package lk.ijse.pos.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.OrderDetails;
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
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetails orderDetails, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"INSERT INTO Order_detail VALUES (?,?,?,?,?)",orderDetails.getoId(),
                orderDetails.getiCode(), orderDetails.getQty(), orderDetails.getPrice(), orderDetails.getTotal());
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetails orderDetails, Connection connection) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public OrderDetails search(String oId, Connection connection) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");

    }

    @Override
    public ObservableList<OrderDetails> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT * FROM Order_detail");

        ObservableList<OrderDetails> obList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            OrderDetails orderDetails = new OrderDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            );

            obList.add(orderDetails);
        }

        return obList;
    }

    @Override
    public ArrayList<OrderDetails> searchOrderDetail(String oId, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection,"SELECT * FROM Order_detail WHERE oId =?",oId
        );
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        while (rst.next()){
            orderDetails.add(new OrderDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5)
            ));
        }
        return orderDetails;
    }
}
